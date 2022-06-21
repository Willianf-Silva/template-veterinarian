package br.com.wnfasolutions.veterinarian.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.veterinarian.dto.request.ServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.ServiceResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Servicos e ou Produtos")
public interface ServiceResouceSwagger {

	@ApiOperation("Incluir um novo produto ou serviço.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ServiceResponseDTO> newService(
			@ApiParam("Serviço ou produto que será incluído.") ServiceRequestDTO serviceRequestDTO,
			HttpServletResponse resp);

	@ApiOperation("Atualizar um produto ou serviço existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ServiceResponseDTO> updateService(
			@ApiParam(value = "ID do serviço ou produto", example = "01") Long id,
			@ApiParam("Serviço ou produto que será incluído.") ServiceRequestDTO serviceRequestDTO) throws Exception;

	@ApiOperation("Buscar um produto ou serviço através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ServiceResponseDTO> findById(
			@ApiParam(value = "ID do serviço ou produto", example = "01") Long id) throws Exception;

	@ApiOperation("Desabilitar um produto ou serviço através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ServiceResponseDTO> disableService(
			@ApiParam(value = "ID do serviço ou produto", example = "01") Long id) throws Exception;

	@ApiOperation("Habilitar um produto ou serviço através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<ServiceResponseDTO> activateService(
			@ApiParam(value = "ID do serviço ou produto", example = "01") Long id) throws Exception;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Listar todos os servios cadastrados no banco de dados")
	public ResponseEntity<?> findAll(@ApiIgnore Pageable pageable);
}