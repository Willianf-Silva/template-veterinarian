package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.response.ItemServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ItemServiceDO;

@Mapper
public interface ItemServiceMapper {

	ItemServiceMapper INSTANCE = Mappers.getMapper(ItemServiceMapper.class);

    ItemServiceResponseDTO toResponseDTO(ItemServiceDO itemServiceDO);
}