package cl.tswoo.lab.modelapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.modelapp.models.Customer;
import cl.tswoo.lab.modelapp.repositories.CustomerRepository;

@Service
public class CustomerServiceImplmentation implements CustomerService {
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public List<Customer> listAll() {
		return repository.findAll();
	}

	@Override
	public void save(Customer customer) {
		repository.save(customer);

	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);

	}

	@Override
	public Customer getById(int id) {
		return repository.getById(id);
	}

}
