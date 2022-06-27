package br.com.wnfasolutions.veterinarian.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.veterinarian.dto.response.CalendarResponseDTO;
import br.com.wnfasolutions.veterinarian.resource.swagger.CalendarResourceSwagger;
import br.com.wnfasolutions.veterinarian.service.CalendarService;

@RestController
@RequestMapping("/api/v1/calendars")
public class CalendarResource extends ResourceBase<CalendarResponseDTO> implements CalendarResourceSwagger {

	@Autowired
	private CalendarService calendarService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<CalendarResponseDTO>> findAll(Pageable pageable){
		Page<CalendarResponseDTO> response = calendarService.findAll(pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<?> findById(@PathVariable Long id) throws Exception{
		CalendarResponseDTO response = calendarService.findById(id);
		return responderSucessoComItem(response);
	}
	
}
