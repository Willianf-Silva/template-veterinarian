package br.com.wnfasolutions.veterinarian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfasolutions.veterinarian.entity.CalendarDO;

public interface CalendarRepository extends JpaRepository<CalendarDO, Long> {

}