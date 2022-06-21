package br.com.wnfasolutions.veterinarian.dto.response;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.wnfasolutions.veterinarian.dto.request.RoleRequestDTO;
import br.com.wnfasolutions.veterinarian.enums.Situation;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianResponseDTO{

	@ApiModelProperty(notes = "Identificador único", required = true, example = "01")
	private Long id;
	
	@ApiModelProperty(notes = "Primeiro nome", required = true, example = "Paulo")
	private String firstName;
	
	@ApiModelProperty(notes = "Último nome", required = true, example = "Silva")
	private String lastName;
	
	@ApiModelProperty(notes = "CPF", required = true, example = "772.324.346-42")
    private String cpf;
	
    @JsonFormat(pattern = "dd/MM/yyyy")
    @ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate birthDate;
	
    @ApiModelProperty(notes = "E-mail", required = true, example = "teste@test.com.br")
	private String email;
	
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(notes = "Situação atual do recurso", required = true, example = "ATIVO")
    private Situation situation;
	
	@ApiModelProperty(notes = "Registro do veterinário no Conselho Regional de Medicina Veterinária (CRMV)", required = false, example = "99.999")
	private String crmv;
	
	@ApiModelProperty(notes = "Usuário de acesso", required = true, example = "will123")
    private String username;
	
	@ApiModelProperty(notes = "Roles para autorização de acesso", required = true)
	@NotNull
	private Set<RoleRequestDTO> roles;
}