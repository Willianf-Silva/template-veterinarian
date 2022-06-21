package br.com.wnfasolutions.veterinarian.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.wnfasolutions.veterinarian.enums.UnitMeasure;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequestDTO{

	@ApiModelProperty(notes = "Nome do produto ou serviço", required = true, example = "Vacina V10")
	@NotBlank
    private String name;

	@ApiModelProperty(notes = "Valor do produto ou serviço", required = true, example = "100.00")
    @DecimalMin("0.0")
    private double value;

    @ApiModelProperty(notes = "Unidade de Medida do produto ou serviço", required = true, example = "SERVICO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private UnitMeasure unitMeasure;
}