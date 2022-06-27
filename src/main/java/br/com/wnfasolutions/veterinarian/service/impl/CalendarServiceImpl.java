package br.com.wnfasolutions.veterinarian.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;
import br.com.wnfasolutions.veterinarian.entity.CalendarDO;
import br.com.wnfasolutions.veterinarian.exception.ResourceNotFoundException;
import br.com.wnfasolutions.veterinarian.repository.CalendarRepository;
import br.com.wnfasolutions.veterinarian.service.CalendarService;

@Service
public class CalendarServiceImpl implements CalendarService {
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
}
