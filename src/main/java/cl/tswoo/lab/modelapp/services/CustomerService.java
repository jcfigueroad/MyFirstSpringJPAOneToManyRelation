package cl.tswoo.lab.modelapp.services;

import java.util.List;

import cl.tswoo.lab.modelapp.models.Customer;


public interface CustomerService {
	
	List<Customer> listAll();
	void save(Customer customer);
	void delete(int id);
	Customer getById(int id); 
}
