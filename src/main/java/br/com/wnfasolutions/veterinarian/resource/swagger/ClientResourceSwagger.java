package br.com.wnfasolutions.veterinarian.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.veterinarian.dto.request.ClientRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ClientResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Cliente")
public interface ClientResourceSwagger {

	@ApiOperation("Incluir um novo cliente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ClientResponseDTO> newClient(
			@ApiParam("Cliente que será incluído.") ClientRequestDTO clientRequestDTO, HttpServletResponse resp)
			throws Exception;

	@ApiOperation("Atualizar um cliente existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ClientResponseDTO> updateClient(@ApiParam(value = "ID do cliente", example = "01") Long id,
			@ApiParam("Cliente que será incluído.") ClientRequestDTO clientRequestDTO) throws Exception;

	@ApiOperation("Buscar um cliente através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ClientResponseDTO> findById(@ApiParam(value = "ID do cliente", example = "01") Long id)
			throws Exception;

	@ApiOperation("Desabilitar um cliente através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ClientResponseDTO> disableClient(@ApiParam(value = "ID do cliente", example = "01") Long id)
			throws Exception;

	@ApiOperation("Habilitar um cliente através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ClientResponseDTO> activateClient(@ApiParam(value = "ID do cliente", example = "01") Long id)
			throws Exception;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Lista todos os clientes cadastrados no banco de dados")
	public ResponseEntity<Page<ClientResponseDTO>> findAll(@ApiIgnore Pageable pageable);
}
