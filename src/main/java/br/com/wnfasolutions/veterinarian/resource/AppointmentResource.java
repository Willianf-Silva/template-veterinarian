package br.com.wnfasolutions.veterinarian.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestUpdateDTO;
import br.com.wnfasolutions.veterinarian.dto.request.ItemServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO;
import br.com.wnfasolutions.veterinarian.event.ResourceCreatedEvent;
import br.com.wnfasolutions.veterinarian.repository.filter.AppointmentFilter;
import br.com.wnfasolutions.veterinarian.resource.swagger.AppointmentResourceSwagger;
import br.com.wnfasolutions.veterinarian.service.AppointmentService;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentResource extends ResourceBase<AppointmentResponseDTO> implements AppointmentResourceSwagger {
	@Autowired
	private AppointmentService appointmentService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AppointmentResponseDTO> newAppointment(
			@RequestBody @Valid AppointmentRequestDTO appointmentRequestDTO, HttpServletResponse resp)
			throws Exception {

		AppointmentResponseDTO response = appointmentService.createNewAppointment(appointmentRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable Long id,
			@RequestBody @Valid AppointmentRequestUpdateDTO appointmentRequestUpdateDTO) throws Exception {
		appointmentService.updateAppointment(id, appointmentRequestUpdateDTO);
		return responderSucesso();
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<AppointmentResponseDTO>> findAll(AppointmentFilter appointmentFilter, Pageable pageable) {
		Page<AppointmentResponseDTO> response = appointmentService.findAll(appointmentFilter, pageable);
		return responderListaDeItensPaginada(response);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<AppointmentResponseDTO> findOneById(@PathVariable Long id) throws Exception {
		AppointmentResponseDTO response = appointmentService.findOneById(id);
		return responderSucessoComItem(response);
	}

	@PatchMapping("/{id}/complete")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AppointmentResponseDTO> completeAppointment(@PathVariable Long id) throws Exception {
		appointmentService.completeAppointment(id);
		return responderSucesso();
	}

	@PatchMapping("/{id}/cancel")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable Long id) throws Exception {
		appointmentService.cancelAppointment(id);
		return responderSucesso();
	}

	@PatchMapping("/{id}/incluir/item")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AppointmentResponseDTO> incluirItemAppointment(@PathVariable Long id,
			@RequestBody @Valid List<ItemServiceRequestDTO> itemServiceRequestDTO) throws Exception {
		appointmentService.incluirItemAppointment(id, itemServiceRequestDTO);
		return responderSucesso();
	}

	@PatchMapping("/{id}/remover/item/{idItem}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<AppointmentResponseDTO> removerItemAppointment(@PathVariable Long id,
			@PathVariable Long idItem) throws Exception {
		appointmentService.removerItemAppointment(id, idItem);
		return responderSucesso();
	}
}
