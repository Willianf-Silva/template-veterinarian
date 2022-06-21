package br.com.wnfasolutions.veterinarian.resource.swagger;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestUpdateDTO;
import br.com.wnfasolutions.veterinarian.dto.request.ItemServiceRequestDTO;
import br.com.wnfasolutions.veterinarian.dto.response.AppointmentResponseDTO;
import br.com.wnfasolutions.veterinarian.repository.filter.AppointmentFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Consultas Veterinária")
public interface AppointmentResourceSwagger {

	@ApiOperation("Incluir uma nova consulta.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> newAppointment(
			@ApiParam("Dados da consulta que será incluída.") AppointmentRequestDTO appointmentRequestDTO,
			HttpServletResponse resp) throws Exception;

	@ApiOperation("Atualizar parcialmente uma nova consulta existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> updateAppointment(
			@ApiParam(value = "ID da consulta", example = "01") Long id,
			@ApiParam("Dados da consulta que será alterado.") AppointmentRequestUpdateDTO appointmentRequestUpdateDTO) throws Exception;

	@ApiOperation("Finaliza uma consulta que foi atendida.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> completeAppointment(
			@ApiParam(value = "ID da consulta", example = "01") Long id) throws Exception;

	@ApiOperation("Cancela uma consulta que não foi atendida.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> cancelAppointment(
			@ApiParam(value = "ID da consulta", example = "01") Long id) throws Exception;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "1"),
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@ApiOperation("Listar todas as consultas cadastrados no banco de dados")
	public ResponseEntity<Page<AppointmentResponseDTO>> findAll(AppointmentFilter appointmentFilter, @ApiIgnore Pageable pageable);

	@ApiOperation("Buscar uma consulta através do identificador")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> findOneById(@ApiParam(value = "ID da consulta", example = "01") Long id) throws Exception;

	@ApiOperation("Incluir itens em uma consulta existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> incluirItemAppointment(
			@ApiParam(value = "ID da consulta", example = "01") Long id,
			@ApiParam("Dados do produto ou serviço que será incluído na consulta.") List<ItemServiceRequestDTO> itemServiceRequestDTO) throws Exception;

	@ApiOperation("Remover um item em uma consulta existente.")
	@ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	public ResponseEntity<AppointmentResponseDTO> removerItemAppointment(
			@ApiParam(value = "ID da consulta", example = "01") Long id,
			@ApiParam(value = "ID do item", example = "01") Long idItem) throws Exception;
}