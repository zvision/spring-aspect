package com.alaf;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.alaf.model.Customer;
import com.alaf.service.CustomerService;


@SpringBootApplication
@EntityScan("com.alaf.model")
public class App {
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    
    @Autowired 
	private CustomerService customerService; 

	@PostConstruct
	public void init() {
		Customer aCustomer = new Customer();
		aCustomer.setFirstName("Kalle");
		aCustomer.setLastName("Anka");
		aCustomer.setAge(20);
		aCustomer.setEmail("kalle.anka@ankeborg.com");
		aCustomer.setEmail1("kalle.anka@abc.com");
		customerService.saveCustomer(aCustomer);
	}
  
}