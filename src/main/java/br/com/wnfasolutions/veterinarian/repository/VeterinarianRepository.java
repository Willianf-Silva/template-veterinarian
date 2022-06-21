package br.com.wnfasolutions.veterinarian.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.veterinarian.entity.VeterinarianDO;

@Repository
public interface VeterinarianRepository extends JpaRepository<VeterinarianDO, Long>{

	Optional<VeterinarianDO> findByUsername(String username);

}