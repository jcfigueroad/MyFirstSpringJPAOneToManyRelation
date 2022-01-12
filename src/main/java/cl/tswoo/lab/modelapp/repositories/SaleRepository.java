package cl.tswoo.lab.modelapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.tswoo.lab.modelapp.models.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

}
