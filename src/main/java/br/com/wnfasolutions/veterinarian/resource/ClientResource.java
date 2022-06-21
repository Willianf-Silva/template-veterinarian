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

import br.com.wnfasolutions.veterinarian.dto.request.ClientRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import br.com.wnfasolutions.veterinarian.event.ResourceCreatedEvent;
import br.com.wnfasolutions.veterinarian.resource.swagger.ClientResourceSwagger;
import br.com.wnfasolutions.veterinarian.service.ClientService;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientResource extends ResourceBase<ClientResponseDTO>
		implements ClientResourceSwagger {

	@Autowired
	private ClientService clientService;

	@Autowired
	private ApplicationEventPublisher publicarEvento;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClientResponseDTO> newClient(
			@RequestBody @Valid ClientRequestDTO clientRequestDTO, HttpServletResponse resp)
			throws Exception {

		ClientResponseDTO response = clientService.createNewClient(clientRequestDTO);
		publicarEvento.publishEvent(new ResourceCreatedEvent(this, resp, response.getId()));
		return responderItemCriado(response);
	}

	@GetMapping
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Page<ClientResponseDTO>> findAll(Pageable pageable) {
		Page<ClientResponseDTO> response = clientService.findAll(pageable);
		return responderListaDeItensPaginada(response);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id,
			@RequestBody @Valid ClientRequestDTO clientRequestDTO) throws Exception {

		ClientResponseDTO response = clientService.updateClient(id, clientRequestDTO);
		return responderSucessoComItem(response);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('read')")
	public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) throws Exception {
		ClientResponseDTO response = clientService.findOneById(id);
		return responderSucessoComItem(response);
	}
	
	@PatchMapping("/desativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClientResponseDTO> disableClient(@PathVariable Long id) throws Exception {
		clientService.disableClient(id);
		return responderSucesso();
	}

	@PatchMapping("/ativar/{id}")
	@PreAuthorize("hasRole('ROLE_OPERATOR') and #oauth2.hasScope('write')")
	public ResponseEntity<ClientResponseDTO> activateClient(@PathVariable Long id) throws Exception {
		clientService.activateClient(id);
		return responderSucesso();
	}
}
