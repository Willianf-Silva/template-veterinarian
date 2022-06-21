package br.com.wnfasolutions.veterinarian.dto.response;

import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemServiceResponseDTO {

	@ApiModelProperty(notes = "Identificador único para cada item", required = true, example = "01")
	private Long id;
	
	@ApiModelProperty(notes = "Quantidade do produto ou serviço escolhido", required = true, example = "02")
	private Integer quantity;
	
	@ApiModelProperty(notes = "Valor total para o produto ou serviço escolhido", required = true)
	@NumberFormat(pattern = "#,##0.00")
	private Double sum;

	@ApiModelProperty(notes = "Dados do produto ou serviço escolhido", required = true)
	private ServiceResponseDTO service;
}
