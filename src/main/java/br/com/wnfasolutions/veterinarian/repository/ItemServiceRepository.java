package br.com.wnfasolutions.veterinarian.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wnfasolutions.veterinarian.entity.ItemServiceDO;

public interface ItemServiceRepository extends JpaRepository<ItemServiceDO, Long>{

}
