package br.com.wnfasolutions.veterinarian.resource.swagger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.veterinarian.dto.request.VeterinarianRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.VeterinarianResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Usuario")
public interface VeterinarianResourceSwagger {

	@ApiOperation("Incluir um novo veterinário.")
	public ResponseEntity<VeterinarianResponseDTO> newVeterinarian(
			@ApiParam("Veterinário que será incluído.") VeterinarianRequestDTO veterinarianRequestDTO,
			HttpServletResponse resp) throws Exception;

	@ApiOperation("Atualizar um veterinário existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<VeterinarianResponseDTO> updateVeterinarian(
			@ApiParam(value = "ID do veterinário", example = "01") Long id,
			@ApiParam("Veterinário que será incluído.") VeterinarianRequestDTO veterinarianRequestDTO) throws Exception;

	@ApiOperation("Buscar um veterinário através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<VeterinarianResponseDTO> findById(
			@ApiParam(value = "ID do veterinário", example = "01") Long id) throws Exception;

	@ApiOperation("Desabilitar um veterinário através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<VeterinarianResponseDTO> disableVeterinarian(
			@ApiParam(value = "ID do veterinário", example = "01") Long id) throws Exception;

	@ApiOperation("Habilitar um veterinário através do identificador.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<VeterinarianResponseDTO> activateVeterinarian(
			@ApiParam(value = "ID do veterinário", example = "01") Long id) throws Exception;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Listar todos veterinárias(os) cadastrados no banco de dados")
	public ResponseEntity<Page<VeterinarianResponseDTO>> findAll(@ApiIgnore Pageable pageable);
}