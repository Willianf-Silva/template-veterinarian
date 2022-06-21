package br.com.wnfasolutions.veterinarian.resource;

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

import br.com.wnfasolutions.veterinarian.dto.request.ServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import br.com.wnfasolutions.veterinarian.event.ResourceCreatedEvent;
import br.com.wnfasolutions.veterinarian.resource.swagger.ServiceResouceSwagger;
import br.com.wnfasolutions.veterinarian.service.ServiceService;

@RestController
@RequestMapping("/api/v1/services")
public class ServiceResource extends ResourceBase<ServiceResponseDTO> implements ServiceResouceSwagger {
	@Autowired
	private ServiceService servicesService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<ServiceResponseDTO> newService(@RequestBody @Valid ServiceRequestDTO serviceRequestDTO,
			HttpServletResponse resp) {
		ServiceResponseDTO response = servicesService.createNewService(serviceRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<ServiceResponseDTO> updateService(@PathVariable Long id,
			@RequestBody @Valid ServiceRequestDTO serviceRequestDTO) throws Exception {

		ServiceResponseDTO response = servicesService.updateService(id, serviceRequestDTO);
		return responderSucessoComItem(response);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<ServiceResponseDTO> findById(@PathVariable Long id) throws Exception {
		ServiceResponseDTO response = servicesService.findById(id);
		return responderSucessoComItem(response);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<?> findAll(Pageable pageable) {
		Page<ServiceResponseDTO> response = servicesService.findAll(pageable);
		return responderListaDeItensPaginada(response);
	}

	@PatchMapping("/desativar/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<ServiceResponseDTO> disableService(@PathVariable Long id) throws Exception {
		servicesService.disableService(id);
		return responderSucesso();
	}

	@PatchMapping("/ativar/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<ServiceResponseDTO> activateService(@PathVariable Long id) throws Exception {
		servicesService.activateService(id);
		return responderSucesso();
	}
}