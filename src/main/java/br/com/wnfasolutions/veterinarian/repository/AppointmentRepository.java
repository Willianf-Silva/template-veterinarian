package br.com.wnfasolutions.veterinarian.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wnfasolutions.veterinarian.entity.AppointmentDO;

public interface AppointmentRepository extends JpaRepository<AppointmentDO, Long> {

	@Query(value = "SELECT a.* FROM appointment AS a WHERE a.status = ?1 ORDER BY a.date, a.veterinarian_id", 
			countQuery = "SELECT count(*) FROM appointment AS a WHERE a.status = ?1", 
			nativeQuery = true)
	Page<AppointmentDO> findByStatus(String status, Pageable pageable);

	@Query(value = "SELECT a.* FROM appointment AS a WHERE a.date = ?1 ORDER BY a.veterinarian_id, a.client_id", 
			countQuery = "SELECT count(*) FROM appointment AS a WHERE a.date = ?1", 
			nativeQuery = true)
	Page<AppointmentDO> findByDate(String date, Pageable pageable);
	
	@Query(value = "SELECT a.* FROM appointment AS a WHERE a.status = ?1 AND a.date = ?2 ORDER BY a.veterinarian_id, a.client_id", 
			countQuery = "SELECT count(*) FROM appointment AS a WHERE a.status = ?1 AND a.date = ?2", 
			nativeQuery = true)
	Page<AppointmentDO> findByStatusAndDate(String status, String date, Pageable pageable);
}