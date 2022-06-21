package br.com.wnfasolutions.veterinarian.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestUpdateDTO;
import br.com.wnfasolutions.veterinarian.dto.request.ItemServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;
import br.com.wnfasolutions.veterinarian.entity.ClientDO;
import br.com.wnfasolutions.veterinarian.entity.ItemServiceDO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;
import br.com.wnfasolutions.veterinarian.enums.Status;
import br.com.wnfasolutions.veterinarian.exception.AppointmentServiceEmptyException;
import br.com.wnfasolutions.veterinarian.exception.RecursoNaoEstaAtivoException;
import br.com.wnfasolutions.veterinarian.exception.ResourceIndisponivelParaAlteracaoException;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.mapper.AppointmentMapper;
import br.com.wnfasolutions.veterinarian.repository.AppointmentRepository;
import br.com.wnfasolutions.veterinarian.repository.ItemServiceRepository;
import br.com.wnfasolutions.veterinarian.repository.filter.AppointmentFilter;
import br.com.wnfasolutions.veterinarian.service.AppointmentService;
import br.com.wnfasolutions.veterinarian.service.ClientService;
import br.com.wnfasolutions.veterinarian.service.ServiceService;
import br.com.wnfasolutions.veterinarian.service.VeterinarianService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private ServiceService serviceService;

	@Autowired
	private VeterinarianService veterinarianService;

	@Autowired
	private ClientService clientService;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	ItemServiceRepository itemServiceRepository;

	private final AppointmentMapper appointmentMapper = AppointmentMapper.INSTANCE;

	@Override
	@Transactional
	public AppointmentResponseDTO createNewAppointment(AppointmentRequestDTO appointmentRequestDTO) throws Exception {
		List<ItemServiceDO> itens = salvarItens(montarItens(appointmentRequestDTO.getItemService()));
		AppointmentDO appointment = montarAppointment(appointmentRequestDTO, itens);
		return convertToResponse(appointmentRepository.save(appointment));
	}

	@Override
	public AppointmentResponseDTO updateAppointment(Long id, AppointmentRequestUpdateDTO appointmentRequestUpdateDTO)
			throws Exception {
		AppointmentDO appointmentOld = verificaSeExiste(id);
		verificarStatusParaAlteracao(appointmentOld);
		if (appointmentOld.getVeterinarian().getId() != appointmentRequestUpdateDTO.getIdVeterinarian()) {
			VeterinarianDO veterinarianDO = obterVeterinarioAtivo(appointmentRequestUpdateDTO.getIdVeterinarian());
			appointmentOld.setVeterinarian(veterinarianDO);
		}
		BeanUtils.copyProperties(appointmentRequestUpdateDTO, appointmentOld, "id");
		return convertToResponse(appointmentRepository.save(appointmentOld));
	}

	@Override
	public Page<AppointmentResponseDTO> findAll(AppointmentFilter appointmentFilter, Pageable pageable) {
		
		//TODO Refatorar o filtro utilizando API CRITERIA
		
		Page<AppointmentDO> consultas = null;
		Status status = appointmentFilter.getStatus();
		LocalDate date = appointmentFilter.getDate();
		
		if (status != null && date != null) {
			String dateFilter = ""+date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth();
			consultas = appointmentRepository.findByStatusAndDate(status.name(), dateFilter, pageable);
		}else if (status != null) {
			consultas = appointmentRepository.findByStatus(status.name(), pageable);
		} else if (date != null) {
			String dateFilter = ""+date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth();
			consultas = appointmentRepository.findByDate(dateFilter, pageable);
		} else {
			consultas = appointmentRepository.findAll(pageable);
		}

		List<AppointmentResponseDTO> response = consultas.stream().map(appointmentMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, consultas.getTotalElements());
	}

	@Override
	public void completeAppointment(Long id) throws Exception {
		alterarStatusServico(id, Status.FINALIZADO);
	}

	@Override
	public void cancelAppointment(Long id) throws Exception {
		alterarStatusServico(id, Status.CANCELADO);
	}

	@Override
	public AppointmentResponseDTO findOneById(Long id) throws Exception {
		return convertToResponse(verificaSeExiste(id));
	}

	@Override
	public void incluirItemAppointment(Long idAppointment, List<ItemServiceRequestDTO> itemServiceRequestDTO)
			throws Exception {
		AppointmentDO appointment = verificaSeExiste(idAppointment);
		verificarStatusParaAlteracao(appointment);
		List<ItemServiceDO> itens = salvarItens(montarItens(itemServiceRequestDTO));
		appointment.getItemService().addAll(itens);
		appointment.setTotal(somarTotalProdutos(appointment.getItemService()));
		appointmentRepository.save(appointment);
	}

	@Override
	public void removerItemAppointment(Long idAppointment, Long idItem) throws Exception {
		AppointmentDO appointment = verificaSeExiste(idAppointment);
		verificarStatusParaAlteracao(appointment);
		List<ItemServiceDO> itens = appointment.getItemService().stream().filter(item -> item.getId() != idItem)
				.collect(Collectors.toList());
		if (itens.isEmpty()) {
			throw new AppointmentServiceEmptyException();
		}
		appointment.setItemService(itens);
		appointment.setTotal(somarTotalProdutos(appointment.getItemService()));
		appointmentRepository.save(appointment);
	}

	private void verificarStatusParaAlteracao(AppointmentDO appointment) throws Exception {
		if (!appointment.getStatus().equals(Status.PENDENTE)) {
			throw new ResourceIndisponivelParaAlteracaoException();
		}
	}

	private List<ItemServiceDO> salvarItens(List<ItemServiceDO> itens) {
		List<ItemServiceDO> itensSaved = new ArrayList<>();
		itens.stream().forEach(item -> {
			itensSaved.add(itemServiceRepository.save(item));
		});
		return itensSaved;
	}

	private AppointmentResponseDTO convertToResponse(AppointmentDO appointmentDO) {
		return appointmentMapper.toResponseDTO(appointmentDO);
	}

	private AppointmentDO montarAppointment(AppointmentRequestDTO appointmentRequestDTO, List<ItemServiceDO> itens)
			throws Exception {
		AppointmentDO consultaDO = new AppointmentDO();
		consultaDO.setStatus(Status.PENDENTE);
		consultaDO.setDateStatus(LocalDate.now());
		consultaDO.setDate(appointmentRequestDTO.getDate());
		consultaDO.setItemService(itens);
		consultaDO.setTotal(somarTotalProdutos(itens));
		consultaDO.setVeterinarian(obterVeterinarioAtivo(appointmentRequestDTO.getIdVeterinarian()));
		consultaDO.setClient(obterClientAtivo(appointmentRequestDTO.getIdclient()));
		return consultaDO;
	}

	private ClientDO obterClientAtivo(Long idclient) throws Exception {
		ClientDO clientDO = clientService.findById(idclient);
		if (!clientDO.ativo()) {
			throw new RecursoNaoEstaAtivoException();
		}
		return clientDO;
	}

	private void alterarStatusServico(Long id, Status status) throws Exception {
		AppointmentDO appointmentDO = verificaSeExiste(id);
		verificarStatusParaAlteracao(appointmentDO);

		appointmentDO.setStatus(status);
		appointmentDO.setDateStatus(LocalDate.now());
		appointmentRepository.save(appointmentDO);
	}

	private AppointmentDO verificaSeExiste(Long id) throws Exception {
		Optional<AppointmentDO> serviceOptional = appointmentRepository.findById(id);
		if (serviceOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return serviceOptional.get();
	}

	private List<ItemServiceDO> montarItens(List<ItemServiceRequestDTO> itens) throws Exception {
		verificarListaVazia(itens);
		List<ItemServiceDO> itensDO = new ArrayList<>();

		for (ItemServiceRequestDTO item : itens) {
			ItemServiceDO itemServiceDO = new ItemServiceDO();
			ServiceDO serviceDO = serviceService.findActiveById(item.getServiceId());
			Integer quantity = item.getQuantity();
			double sum = serviceDO.getValue() * quantity;

			itemServiceDO.setService(serviceDO);
			itemServiceDO.setQuantity(quantity);
			itemServiceDO.setSum(sum);
			itensDO.add(itemServiceDO);
		}

		return itensDO;
	}

	private VeterinarianDO obterVeterinarioAtivo(Long idVeterinarian) throws Exception {
		VeterinarianDO veterinarianDO = veterinarianService.findById(idVeterinarian);
		if (!veterinarianDO.ativo()) {
			throw new RecursoNaoEstaAtivoException();
		}
		return veterinarianDO;
	}

	private double somarTotalProdutos(List<ItemServiceDO> itens) {
		double total = 0;
		for (ItemServiceDO item : itens) {
			total += item.getSum();
		}
		return total;
	}

	private void verificarListaVazia(List<?> itens) throws ResourceNotFoundException {
		if (itens.isEmpty()) {
			throw new ResourceNotFoundException();
		}
	}
}