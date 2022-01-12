package cl.tswoo.lab.modelapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.tswoo.lab.modelapp.models.Sale;
import cl.tswoo.lab.modelapp.repositories.SaleRepository;

@Service
public class SaleServiceImplmentation implements SaleService {
	
	@Autowired
	private SaleRepository repository;

	@Override
	public List<Sale> listAll() {
		return repository.findAll();
	}

	@Override
	public void save(Sale sale) {
		repository.save(sale);

	}

	@Override
	public void delete(int id) {
		repository.deleteById(id);

	}

	@Override
	public Sale getById(int id) {
		return repository.getById(id);
	}

}
