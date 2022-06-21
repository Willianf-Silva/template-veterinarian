package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.request.VeterinarianRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;

@Mapper
public interface VeterinarianMapper {

	VeterinarianMapper INSTANCE = Mappers.getMapper(VeterinarianMapper.class);

	VeterinarianDO toModel(VeterinarianRequestDTO veterinarianRequestDTO);

	VeterinarianResponseDTO toResponseDTO(VeterinarianDO veterinarianDO);
}