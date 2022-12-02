package br.com.wnfasolutions.veterinarian.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.wnfasolutions.veterinarian.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_appointment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	@Column(nullable = false)
	private Double total;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Column(nullable = true)
	private LocalDate dateStatus;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "appointment_id")
	private List<ItemServiceDO> itemService;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id")
	private ClientDO client;
}