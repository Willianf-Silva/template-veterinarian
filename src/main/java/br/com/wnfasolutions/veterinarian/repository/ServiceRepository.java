package br.com.wnfasolutions.veterinarian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfasolutions.veterinarian.entity.ServiceDO;

public interface ServiceRepository extends JpaRepository<ServiceDO, Long>{
}
