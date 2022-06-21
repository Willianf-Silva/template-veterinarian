package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.request.ServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.ServiceDO;

@Mapper
public interface ServiceMapper {

	ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    ServiceDO toModel(ServiceRequestDTO serviceRequestDTO);
    ServiceDO toModel(ServiceResponseDTO serviceResponseDTO);

    ServiceResponseDTO toResponseDTO(ServiceDO serviceDO);

}