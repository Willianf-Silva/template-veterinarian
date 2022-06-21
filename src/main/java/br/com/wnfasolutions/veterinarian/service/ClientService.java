package br.com.wnfasolutions.veterinarian.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.veterinarian.dto.request.ClientRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ClientDO;

public interface ClientService {

	ClientResponseDTO createNewClient(ClientRequestDTO clientRequestDTO);

	Page<ClientResponseDTO> findAll(Pageable pageable);

	ClientDO findById(Long idClient) throws Exception;
	
	ClientResponseDTO findOneById(Long idClient) throws Exception;

	ClientResponseDTO updateClient(Long id, ClientRequestDTO clientRequestDTO) throws Exception;

	void activateClient(Long id) throws Exception;

	void disableClient(Long id) throws Exception;
}