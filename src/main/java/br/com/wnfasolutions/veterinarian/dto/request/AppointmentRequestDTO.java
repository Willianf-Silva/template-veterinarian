package br.com.wnfasolutions.veterinarian.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {

	@ApiModelProperty(notes = "Data do agendamento da consulta", required = true, example = "24/12/2022")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

	@ApiModelProperty(notes = "Item contendo a quantidade e o produto ou serviço adquirido", required = true)
	@NotNull
	private List<ItemServiceRequestDTO> itemService;

	@ApiModelProperty(notes = "Identificador único do cliente", required = true, example = "01")
	@NotNull
	private Long idClient;
	
	@ApiModelProperty(notes = "Identificador único do veterinário", required = true, example = "01")
	@NotNull
	private Long idVeterinarian;

}
