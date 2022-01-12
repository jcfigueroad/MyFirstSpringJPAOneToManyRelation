package cl.tswoo.lab.modelapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.tswoo.lab.modelapp.models.Customer;
import cl.tswoo.lab.modelapp.models.Sale;
import cl.tswoo.lab.modelapp.services.CustomerServiceImplmentation;
import cl.tswoo.lab.modelapp.services.SaleServiceImplmentation;

@RequestMapping("/sale")
@Controller
public class SaleController {
	
	@Autowired
	SaleServiceImplmentation saleService;
	@Autowired
	CustomerServiceImplmentation customerService;
	
	@GetMapping({"", "/","/list"})
	public String list(Model model) {
		List<Sale> list = saleService.listAll();
		model.addAttribute("sales", list);
		return "sale/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		saleService.delete(id);
		return "redirect:list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		Sale sale = saleService.getById(id);
		model.addAttribute("sale", sale);
		List<Customer> listCustomers = customerService.listAll();
		model.addAttribute("customers", listCustomers);
		return "sale/form";
	}
	
	@PostMapping("/save")
	public String update(@Valid Sale sale, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			List<Customer> listCustomers = customerService.listAll();
			model.addAttribute("customers", listCustomers);
			return "sale/form";
		}
		
		System.out.println(sale.toString());
		
		saleService.save(sale);
		
		return "redirect:list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Sale sale = new Sale();
		List<Customer> listCustomers = customerService.listAll();
		model.addAttribute("sale", sale);
		model.addAttribute("customers", listCustomers);
		return "sale/form";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam Integer id, Model model) {
		Sale sale = saleService.getById(id);
		model.addAttribute("sale", sale);
		return "sale/detail";
	}

}
