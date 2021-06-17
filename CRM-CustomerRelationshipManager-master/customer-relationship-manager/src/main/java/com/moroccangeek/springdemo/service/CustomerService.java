package com.moroccangeek.springdemo.service;

import java.util.List;

import com.moroccangeek.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer c);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String theSearchName);
}
