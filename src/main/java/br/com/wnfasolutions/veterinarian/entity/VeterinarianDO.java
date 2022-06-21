package br.com.wnfasolutions.veterinarian.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.wnfasolutions.veterinarian.enums.Situation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_veterinarian")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeterinarianDO implements UserDetails{
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
    
    @Column(nullable = false, unique = true)
    private String crmv;
    
    @NotNull
    @Column(unique = true)
    private String username;
    
    @NotNull
    private String password;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Phone> phones;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Address> address;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_veterinarian_role", // nome da tabela que realiza o relacionamento entre user e role
			joinColumns = @JoinColumn(name = "veterinarian_id"), // chave primaria da tabela atual
			inverseJoinColumns = @JoinColumn(name = "tb_role_id") // chave primaria da outra tabrela
	)
	private Set<RoleDO> roles; // Foi utilizado Set para forçar que o usuário não tenha repetição nas roles

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(x -> new SimpleGrantedAuthority(x.getRoleName()))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return ativo();
	}
    
    public Boolean ativo() {
    	return getSituation().equals(Situation.ATIVO);
    }
}
