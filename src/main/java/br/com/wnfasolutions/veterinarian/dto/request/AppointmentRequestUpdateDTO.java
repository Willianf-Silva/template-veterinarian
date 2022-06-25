package br.com.wnfasolutions.veterinarian.dto.request;

import java.time.LocalDate;

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
public class AppointmentRequestUpdateDTO {

	@ApiModelProperty(notes = "Data do agendamento da consulta", required = true, example = "24/12/2022")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

//	@ApiModelProperty(notes = "Identificador único do veterinário", required = true, example = "01")
//	@NotNull
//	private Long idVeterinarian;
}
