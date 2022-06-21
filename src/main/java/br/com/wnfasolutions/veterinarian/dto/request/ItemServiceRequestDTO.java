package br.com.wnfasolutions.veterinarian.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemServiceRequestDTO {
	@ApiModelProperty(notes = "Quantidade do produto ou serviço escolhido", required = true, example = "02")
	@Min(value = 1)
	private Integer quantity;
	
	@ApiModelProperty(notes = "Identificador do produto ou serviço escolhido", required = true, example = "01")
	@NotNull
	private Long serviceId;
}