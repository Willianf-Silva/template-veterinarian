package br.com.wnfasolutions.veterinarian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfasolutions.veterinarian.entity.CalendarDO;
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
}
