package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.request.ClientRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ClientDO;

@Mapper
public interface ClientMapper {

	ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

	ClientDO toModel(ClientRequestDTO clientRequestDTO);

	ClientResponseDTO toResponseDTO(ClientDO clientDO);
}