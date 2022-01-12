package cl.tswoo.lab.modelapp.services;

import java.util.List;

import cl.tswoo.lab.modelapp.models.Sale;


public interface SaleService {
	
	List<Sale> listAll();
	void save(Sale sale);
	void delete(int id);
	Sale getById(int id); 
}
