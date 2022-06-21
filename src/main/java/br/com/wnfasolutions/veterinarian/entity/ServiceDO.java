package br.com.wnfasolutions.veterinarian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wnfasolutions.veterinarian.enums.Situation;
import br.com.wnfasolutions.veterinarian.enums.UnitMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(nullable = false, length=100, unique = true)
    private String name;

	@Column(nullable = false)
    private double value;

    @Column(nullable = false, length=20) 
    @Enumerated(EnumType.STRING)
    private UnitMeasure unitMeasure;
    
    @Column(nullable = false) 
    @Enumerated(EnumType.STRING)
    private Situation situation;
    
    public Boolean ativo() {
    	return getSituation().equals(Situation.ATIVO);
    }
}