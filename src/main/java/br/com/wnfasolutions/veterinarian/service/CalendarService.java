package br.com.wnfasolutions.veterinarian.service;

import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;
import br.com.wnfasolutions.veterinarian.entity.CalendarDO;

public interface CalendarService {

	/**
	 * Cria um novo calendario
	 * @return
	 */
	CalendarDO createCalendar();
	
	/**
	 * Adiciona uma consulta no calendario
	 * @param idCalendarLong
	 * @param appointmentDO
	 * @return
	 * @throws Exception
	 */
	CalendarDO addAppointment(Long idCalendarLong, AppointmentDO appointmentDO) throws Exception;
}
