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

import br.com.wnfasolutions.veterinarian.dto.request.ClientRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ClientDO;
import br.com.wnfasolutions.veterinarian.enums.Situation;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.mapper.ClientMapper;
import br.com.wnfasolutions.veterinarian.repository.ClientRepository;
import br.com.wnfasolutions.veterinarian.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	private final ClientMapper clientMapper = ClientMapper.INSTANCE;
	
	@Override
	public ClientResponseDTO createNewClient(ClientRequestDTO clientRequestDTO) {		
		ClientDO clienteDO = convertToModel(clientRequestDTO);
		clienteDO.setSituation(Situation.ATIVO);
		return convertToResponse(clientRepository.save(clienteDO));
	}

	@Override
	public Page<ClientResponseDTO> findAll(Pageable pageable) {
		Page<ClientDO> clients = clientRepository.findAll(pageable);
		return new PageImpl<>(convertToResponse(clients), pageable, clients.getTotalElements());
	}

	@Override
	public ClientDO findById(Long id) throws Exception {
		return verificarSeExiste(id);
	}

	@Override
	public ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) throws Exception {
		ClientDO clientDO = verificarSeExiste(id);
		BeanUtils.copyProperties(clientRequestDTO, clientDO, "id");
		return convertToResponse(clientRepository.save(clientDO));
	}


	@Override
	public ClientResponseDTO findOneById(Long id) throws Exception {
		return convertToResponse(verificarSeExiste(id));
	}

	@Override
	public void activateClient(Long id) throws Exception {
		alterarSituacaoClient(id, Situation.ATIVO);
	}

	@Override
	public void disableClient(Long id) throws Exception {
		alterarSituacaoClient(id, Situation.INATIVO);
	}
	
	private void alterarSituacaoClient(Long id, Situation situation) throws Exception {
		ClientDO clientDO = verificarSeExiste(id);
		clientDO.setSituation(situation);
		clientRepository.save(clientDO);
	}
	
	private ClientDO verificarSeExiste(Long id) throws Exception {
		Optional<ClientDO> clientOptional = clientRepository.findById(id);
		if (clientOptional.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return clientOptional.get();
	}
	
	private ClientDO convertToModel(ClientRequestDTO clientRequestDTO) {
		return clientMapper.toModel(clientRequestDTO);
	}
	
	private ClientResponseDTO convertToResponse(ClientDO clientDO) {
		return clientMapper.toResponseDTO(clientDO);
	}
	
	private List<ClientResponseDTO> convertToResponse(Page<ClientDO> clients) {
		return clients.stream()
				.map(clientMapper::toResponseDTO)
				.collect(Collectors.toList());
	}
}
