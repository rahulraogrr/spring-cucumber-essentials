package com.spring.cucumber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.cucumber.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
	
}
