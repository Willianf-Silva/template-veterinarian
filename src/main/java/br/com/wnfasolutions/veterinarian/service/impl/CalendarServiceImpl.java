package br.com.wnfasolutions.veterinarian.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.veterinarian.dto.response.CalendarResponseDTO;
import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;
import br.com.wnfasolutions.veterinarian.entity.CalendarDO;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.mapper.CalendarMapper;
import br.com.wnfasolutions.veterinarian.repository.CalendarRepository;
import br.com.wnfasolutions.veterinarian.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {
	
	private CalendarMapper calendarMapper = CalendarMapper.INSTANCE;
	
	@Autowired
	private CalendarRepository calendarRepository;

	@Override
	public CalendarDO createCalendar() {
		return calendarRepository.save(new CalendarDO());
	}

	@Override
	public CalendarDO addAppointment(Long idCalendarLong, AppointmentDO appointmentDO) throws Exception {
		CalendarDO calendarDO = verificarSeExiste(idCalendarLong);
		calendarDO.getAppointments().add(appointmentDO);
		return calendarRepository.save(calendarDO);
	}

	private CalendarDO verificarSeExiste(Long idCalendarLong) throws Exception {
		Optional<CalendarDO> calendarDO = calendarRepository.findById(idCalendarLong);
		if (calendarDO.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return calendarDO.get();
	}

	@Override
	public Page<CalendarResponseDTO> findAll(Pageable pageable) {
		Page<CalendarDO> calendarsDO = calendarRepository.findAll(pageable);
		List<CalendarResponseDTO> calendarsDTO = calendarsDO.stream()
				.map(calendarMapper::toResponseDTO)
				.collect(Collectors.toList());
		return new PageImpl<>(calendarsDTO, pageable, calendarsDO.getTotalElements());
	}

	@Override
	public CalendarResponseDTO findById(Long id) throws Exception {
		return calendarMapper.toResponseDTO(verificarSeExiste(id));
	}
}
