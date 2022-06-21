package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.RoleResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.RoleDO;

@Mapper
public interface RoleMapper {

	RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
	
	@Mapping(target = "id", ignore = true)
	RoleDO toModel(RoleRequestDTO roleRequestDTO);
	
	RoleResponseDTO toResponseDTO(RoleDO roleDO);
}