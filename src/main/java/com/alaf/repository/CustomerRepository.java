package com.alaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alaf.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Transactional
	void deleteById(Long id);  
}
