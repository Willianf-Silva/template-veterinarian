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

import br.com.wnfasolutions.veterinarian.dto.request.VeterinarianRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import br.com.wnfasolutions.veterinarian.event.ResourceCreatedEvent;
import br.com.wnfasolutions.veterinarian.resource.swagger.VeterinarianResourceSwagger;
import br.com.wnfasolutions.veterinarian.service.VeterinarianService;

@RestController
@RequestMapping("/api/v1/veterinarians")
public class VeterinarianResource extends ResourceBase<VeterinarianResponseDTO> implements VeterinarianResourceSwagger {

	@Autowired
	private VeterinarianService veterinarianService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	public ResponseEntity<VeterinarianResponseDTO> newVeterinarian(
			@RequestBody @Valid VeterinarianRequestDTO veterinarianRequestDTO, HttpServletResponse resp)
			throws Exception {

		VeterinarianResponseDTO response = veterinarianService.createNewVeterinarian(veterinarianRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<VeterinarianResponseDTO>> findAll(Pageable pageable) {
		Page<VeterinarianResponseDTO> response = veterinarianService.findAll(pageable);
		return responderListaDeItensPaginada(response);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<VeterinarianResponseDTO> updateVeterinarian(@PathVariable Long id,
			@RequestBody @Valid VeterinarianRequestDTO veterinarianRequestDTO) throws Exception {

		VeterinarianResponseDTO response = veterinarianService.updateVeterinarian(id, veterinarianRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<VeterinarianResponseDTO> findById(@PathVariable Long id) throws Exception {
		VeterinarianResponseDTO response = veterinarianService.findOneById(id);
		return responderSucessoComItem(response);
	}
	
	@PatchMapping("/desativar/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<VeterinarianResponseDTO> disableVeterinarian(@PathVariable Long id) throws Exception {
		veterinarianService.disableVeterinarian(id);
		return responderSucesso();
	}

	@PatchMapping("/ativar/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') and #oauth2.hasScope('write')")
	public ResponseEntity<VeterinarianResponseDTO> activateVeterinarian(@PathVariable Long id) throws Exception {
		veterinarianService.activateVeterinarian(id);
		return responderSucesso();
	}
	
}
