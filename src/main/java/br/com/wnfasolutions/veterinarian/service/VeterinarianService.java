package br.com.wnfasolutions.veterinarian.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.veterinarian.dto.request.VeterinarianRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;

public interface VeterinarianService {

	VeterinarianResponseDTO createNewVeterinarian(VeterinarianRequestDTO veterinarianRequestDTO) throws Exception;

	Page<VeterinarianResponseDTO> findAll(Pageable pageable);

	VeterinarianDO findById(Long idVeterinarian) throws Exception;

	VeterinarianResponseDTO updateVeterinarian(Long id, VeterinarianRequestDTO veterinarianRequestDTO) throws Exception;

	VeterinarianResponseDTO findOneById(Long id) throws Exception;

	void disableVeterinarian(Long id) throws Exception;

	void activateVeterinarian(Long id) throws Exception;
	
}