package br.com.wnfasolutions.veterinarian.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.wnfasolutions.veterinarian.dto.response.CalendarResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.CalendarDO;

@Mapper
public interface CalendarMapper {

	CalendarMapper INSTANCE = Mappers.getMapper(CalendarMapper.class);
	
	CalendarResponseDTO toResponseDTO(CalendarDO calendarDO);
}