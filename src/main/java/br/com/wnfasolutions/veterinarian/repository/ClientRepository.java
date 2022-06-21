package br.com.wnfasolutions.veterinarian.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wnfasolutions.veterinarian.entity.ClientDO;

@Repository
public interface ClientRepository extends JpaRepository<ClientDO, Long>{

}