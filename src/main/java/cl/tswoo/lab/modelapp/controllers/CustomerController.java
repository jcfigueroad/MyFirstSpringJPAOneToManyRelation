package cl.tswoo.lab.modelapp.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

import cl.tswoo.lab.modelapp.models.Customer;
import cl.tswoo.lab.modelapp.services.CustomerServiceImplmentation;

@RequestMapping("/customer")
@Controller
public class CustomerController {
	
	@Autowired
	CustomerServiceImplmentation service;
	
	@GetMapping({"", "/","/list"})
	public String list(Model model) {
		List<Customer> list = service.listAll();
		model.addAttribute("customers", list);
		return "customer/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		service.delete(id);
		return "redirect:customer/list";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		Customer customer = service.getById(id);
		model.addAttribute("customer", customer);
		model.addAttribute("file", customer.getImage());
		return "customer/form";
	}
	
	@PostMapping("/save")
	public String update(@Valid Customer customer, @RequestParam("file") MultipartFile image, BindingResult result, Model model) {
		
		
		if (result.hasErrors()) {
			return "customer/form";
		}
		
		if(!image.isEmpty()) {
			Path resourceDirectory = Paths.get("uploads");
			//String absolutePath = resourceDirectory.toAbsolutePath().toString();
			try {
				byte[] bytes = image.getBytes();
				Path absoluteFilePath = resourceDirectory.resolve(image.getOriginalFilename());
				Files.write(absoluteFilePath, bytes);
				customer.setImage(image.getOriginalFilename());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		service.save(customer);
		model.addAttribute("customer", customer);
		
		return "redirect:list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "customer/form";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam Integer id, Model model) {
		Customer customer = service.getById(id);
		model.addAttribute("customer", customer);
		return "customer/detail";
	}

}
