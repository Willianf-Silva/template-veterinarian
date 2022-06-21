package br.com.wnfasolutions.veterinarian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfasolutions.veterinarian.entity.ServiceDO;
import br.com.wnfasolutions.veterinarian.enums.Situation;

public interface ServiceRepository extends JpaRepository<ServiceDO, Long>{
}
