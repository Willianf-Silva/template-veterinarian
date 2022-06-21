package br.com.wnfasolutions.veterinarian.repository.filter;

import java.time.LocalDate;

import br.com.wnfasolutions.veterinarian.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentFilter {
	private LocalDate date;
	private Status status;
}