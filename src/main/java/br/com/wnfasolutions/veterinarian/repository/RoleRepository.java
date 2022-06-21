package br.com.wnfasolutions.veterinarian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.veterinarian.entity.RoleDO;

@Repository
public interface RoleRepository extends JpaRepository<RoleDO, Long> {
	RoleDO findByRoleName(String roleName);
}
