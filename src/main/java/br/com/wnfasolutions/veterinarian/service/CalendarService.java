package br.com.wnfasolutions.veterinarian.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfasolutions.veterinarian.dto.response.CalendarResponseDTO;
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

	/**
	 * Buscar todos os calendarios
	 * @param pageable
	 * @return
	 */
	Page<CalendarResponseDTO> findAll(Pageable pageable);

	/**
	 * Buscar calendario através do identificador
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	CalendarResponseDTO findById(Long id) throws Exception;
}
