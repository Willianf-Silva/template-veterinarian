package br.com.wnfasolutions.veterinarian.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.veterinarian.dto.request.ServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;
import br.com.wnfasolutions.veterinarian.enums.Situation;
import br.com.wnfasolutions.veterinarian.exception.RecursoNaoEstaAtivoException;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.mapper.ServiceMapper;
import br.com.wnfasolutions.veterinarian.repository.ServiceRepository;
import br.com.wnfasolutions.veterinarian.service.ServiceService;

@Service
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceRepository serviceRepository;

	private final ServiceMapper serviceMapper = ServiceMapper.INSTANCE;

	@Override
	public ServiceResponseDTO createNewService(ServiceRequestDTO serviceRequestDTO) {
		ServiceDO serviceDO = convertToServiceDO(serviceRequestDTO);
		serviceDO.setSituation(Situation.ATIVO);
		ServiceDO serviceSaved = serviceRepository.save(serviceDO);
		return convertToResponse(serviceSaved);
	}

	@Override
	public ServiceResponseDTO updateService(Long id, ServiceRequestDTO serviceRequestDTO) throws Exception {
		ServiceDO servicoDO = this.verificaServicoExiste(id);
		BeanUtils.copyProperties(serviceRequestDTO, servicoDO, "id");
		return convertToResponse(serviceRepository.save(servicoDO));
	}

	@Override
	public ServiceResponseDTO findById(Long id) throws Exception {
		ServiceDO serviceDO = this.verificaServicoExiste(id);
		return convertToResponse(serviceDO);
	}

	@Override
	public Page<ServiceResponseDTO> findAll(Pageable pageable) {
		Page<ServiceDO> servicesDO = serviceRepository.findAll(pageable);

		List<ServiceResponseDTO> response = servicesDO.stream().map(serviceMapper::toResponseDTO)
				.collect(Collectors.toList());

		return new PageImpl<>(response, pageable, servicesDO.getTotalElements());
	}

	@Override
	public void activateService(Long id) throws Exception {
		alterarStatusServico(id, Situation.ATIVO);
	}

	@Override
	public void disableService(Long id) throws Exception {
		alterarStatusServico(id, Situation.INATIVO);
	}

	@Override
	public ServiceDO findActiveById(Long id) throws Exception {
		return verificaServicoAtivo(verificaServicoExiste(id));
	}

	private void alterarStatusServico(Long id, Situation situation) throws Exception {
		ServiceDO servico = verificaServicoExiste(id);
		servico.setSituation(situation);
		serviceRepository.save(servico);
	}

	private ServiceDO convertToServiceDO(ServiceRequestDTO serviceRequestDTO) {
		return serviceMapper.toModel(serviceRequestDTO);
	}

	private ServiceResponseDTO convertToResponse(ServiceDO serviceDO) {
		return serviceMapper.toResponseDTO(serviceDO);
	}

	private ServiceDO verificaServicoAtivo(ServiceDO servico) throws Exception {
		if (!servico.ativo()) {
			throw new RecursoNaoEstaAtivoException();
		}
		return servico;
	}

	private ServiceDO verificaServicoExiste(Long id) throws Exception {
		Optional<ServiceDO> serviceOptional = serviceRepository.findById(id);
		if (serviceOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return serviceOptional.get();
	}

}