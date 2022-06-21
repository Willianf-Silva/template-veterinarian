package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;

@Mapper
public interface AppointmentMapper {

	AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

	AppointmentDO toModel(AppointmentRequestDTO appointmentRequestDTO);

	AppointmentResponseDTO toResponseDTO(AppointmentDO appointmentDO);
}