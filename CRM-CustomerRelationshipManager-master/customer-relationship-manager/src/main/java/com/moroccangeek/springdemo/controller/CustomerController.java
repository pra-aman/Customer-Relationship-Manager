package com.moroccangeek.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moroccangeek.springdemo.entity.Customer;
import com.moroccangeek.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	
	//need to inject the customer Service
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model model) {
		
		//Get customers from DAO
		List<Customer> theCustomers = customerService.getCustomers();
		
		//theCustomers.sort(null);
		
		//Add the customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		//create model attribute to bind form data
		Customer newCustomer = new Customer();
		
		model.addAttribute("NewCustomer", newCustomer);
		
		return "Customer-AddForm";
	}

	@PostMapping("/saveCustomer")
	public String AddCustomer(@ModelAttribute("NewCustomer") Customer newCustomer) {
		
		customerService.saveCustomer(newCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int id, Model model) {
		
		//Retrieve the customer with the specified Id
		Customer c = customerService.getCustomer(id);
		
		//set customer as a module attribute to pre-populate the form
		model.addAttribute("NewCustomer", c);
		
		//send over to our form
		return "Customer-AddForm";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}

	
	@PostMapping("/searchCustomer")
	public String search(@RequestParam("theSearchName") String theSearchName, 
						 Model model) {
		
		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		 // add the customers to the model
        model.addAttribute("customers", theCustomers);

        return "list-customers";     
	}






}
