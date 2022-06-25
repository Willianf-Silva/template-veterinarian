package br.com.wnfasolutions.veterinarian.dto.response;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.veterinarian.enums.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {

	@ApiModelProperty(notes = "Identificador único da consulta", required = true, example = "01")
	private Long id;
	
	@ApiModelProperty(notes = "Data do agendamento da consulta", required = true, example = "24/12/2022")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;
	
	@ApiModelProperty(notes = "Valor total da consulta", required = true)
	@NumberFormat(pattern = "#,##0.00")
	private Double total;

	@ApiModelProperty(notes = "Status atual da consulta", required = true)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ApiModelProperty(notes = "Dados dos produtos e/ou serviços adquiridos", required = true)
	private List<ItemServiceResponseDTO> itemService;
	
//	@ApiModelProperty(notes = "Dados do veterinário", required = true)
//	private VeterinarianResponseDTO veterinarian;

	@ApiModelProperty(notes = "Dados do cliente", required = true)
	private ClientResponseDTO client;

}
