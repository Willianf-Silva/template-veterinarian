package br.com.wnfasolutions.veterinarian.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wnfasolutions.veterinarian.enums.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_client")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
    private String cpf;
	
	private LocalDate birthDate;
	
	private String email;
	
    @Column(nullable = false) 
    @Enumerated(EnumType.STRING)
    private Situation situation;
	
    private String apelido;
    
//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Phone> phones;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Address> address;
    
    public Boolean ativo() {
    	return getSituation().equals(Situation.ATIVO);
    }
}