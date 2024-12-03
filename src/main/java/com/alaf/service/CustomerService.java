package com.alaf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alaf.exception.ResourceNotFoundException;
import com.alaf.model.Customer;
import com.alaf.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	public Customer getCustomerbyId(Long id) throws ResourceNotFoundException {
		if (customerRepo.findById(id).isPresent()) {
			return customerRepo.findById(id).get();
		} 	
		else {
			throw new ResourceNotFoundException("No such customer id: " + id);
		}
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepo.save(customer);
	}

	public List<Customer> fetchCustomerList() {
		List<Customer> customerList = new ArrayList<>();
		customerRepo.findAll().spliterator().forEachRemaining(customerList::add);
		return customerList;
	}

	public void deleteCustomerbyId(Long id) {
		customerRepo.deleteById(id);
	}
}
