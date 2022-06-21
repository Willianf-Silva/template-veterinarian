package br.com.wnfasolutions.veterinarian.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.veterinarian.dto.request.ServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;

public interface ServiceService {

	/**
	 * Cria um novo serviço ou produto no banco de dados
	 * @param serviceRequestDTO
	 * @return
	 */
	ServiceResponseDTO createNewService(ServiceRequestDTO serviceRequestDTO);

	/**
	 * Atualiza um serviço ou produto existente no banco de dados
	 * @param id
	 * @param serviceRequestDTO
	 * @return
	 * @throws Exception 
	 */
	ServiceResponseDTO updateService(Long id, ServiceRequestDTO serviceRequestDTO) throws Exception;

	/**
	 * Busca um serviço ou produto existente no banco de dados
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	ServiceResponseDTO findById(Long id) throws Exception;

	/**	
	 * Busca todos os serviços e produtos existentes no banco de dados
	 * @return
	 */
	Page<ServiceResponseDTO> findAll(Pageable pageable);

	/**
	 * Ativar um serviço que está desativado
	 * @param id
	 * @throws Exception 
	 */
	void activateService(Long id) throws Exception;

	/**
	 * Desabilitar um serviço
	 * @param id
	 * @throws Exception
	 */
	void disableService(Long id) throws Exception;

	/**
	 * Busca serviço ativo através do identificador
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	ServiceDO findActiveById(Long id) throws Exception;
}