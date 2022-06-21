package br.com.wnfasolutions.veterinarian.dto.request;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

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
public class ClientRequestDTO{

	@NotNull
	@ApiModelProperty(notes = "Primeiro nome", required = true, example = "Paulo")
	private String firstName;

	@NotNull
	@ApiModelProperty(notes = "Último nome", required = true, example = "Silva")
	private String lastName;

	@CPF
	@ApiModelProperty(notes = "CPF", required = true, example = "772.324.346-42")
    private String cpf;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(notes = "Data de nascimento", required = true, example = "15/05/1991")
	private LocalDate birthDate;
	
	@NotNull
	@Email
	@ApiModelProperty(notes = "E-mail", required = true, example = "teste@test.com.br")
	private String email;
	
	@ApiModelProperty(notes = "Apelido do cliente", required = false, example = "Will")
	private String apelido;
}