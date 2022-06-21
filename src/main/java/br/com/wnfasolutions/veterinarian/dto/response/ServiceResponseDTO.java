package br.com.wnfasolutions.veterinarian.dto.response;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wnfasolutions.veterinarian.enums.Situation;
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
public class ServiceResponseDTO {

	@ApiModelProperty(notes = "Identificador único para o serviço", required = true, example = "01")
	private Long id;
	
	@ApiModelProperty(notes = "Nome do produto ou serviço", required = true, example = "Vacina V10")
    private String name;

	@ApiModelProperty(notes = "Valor do produto ou serviço", required = true, example = "100.00")
    private double value;

	@ApiModelProperty(notes = "Unidade de Medida do produto ou serviço", required = true, example = "SERVICO")
    @Enumerated(EnumType.STRING)
    private UnitMeasure unitMeasure;
    
	@ApiModelProperty(notes = "Situação atual do produto ou serviço", required = true, example = "ATIVO")
    @Enumerated(EnumType.STRING)
    private Situation situation;
}
