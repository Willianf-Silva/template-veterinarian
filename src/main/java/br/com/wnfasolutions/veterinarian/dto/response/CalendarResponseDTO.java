package br.com.wnfasolutions.veterinarian.dto.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.wnfasolutions.veterinarian.dto.request.AppointmentRequestDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponseDTO {

	@ApiModelProperty(notes = "Identificador do calendário", required = true, example = "01")
	@NotNull
	private Long id;

	@ApiModelProperty(notes = "Agendamentos para o calendário", required = true)
	private List<AppointmentRequestDTO> appointments;

}
