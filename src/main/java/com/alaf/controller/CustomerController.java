package com.alaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alaf.exception.ResourceNotFoundException;
import com.alaf.model.Customer;
import com.alaf.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return ResponseEntity.ok().body(customer);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<String> getCustomer(@PathVariable("id") String id) {

		try {
			Customer customer = customerService.getCustomerbyId(Long.valueOf(id));
			return ResponseEntity.ok().body(customer.toString());
		} catch (NumberFormatException | ResourceNotFoundException e) {
			e.printStackTrace();
		}

		String jsonMsg = "{\"status\":\"No such customer id: " + id + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonMsg);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long customerId) {

		String jsonMsg = "";

		try {
			Customer customer = customerService.getCustomerbyId(customerId);
			customerService.deleteCustomerbyId(customer.getId());
			jsonMsg = "{\"status\":\"DELETED\"}";
			return ResponseEntity.ok().body(jsonMsg);
		} catch (ResourceNotFoundException e) {			
			e.printStackTrace();
		}

		jsonMsg = "{\"status\":\"No such customer with id " + customerId + "\"}";
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonMsg);

	}

}
