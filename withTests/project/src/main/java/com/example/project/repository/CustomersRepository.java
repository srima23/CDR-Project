package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project.entity.Customers;

public interface CustomersRepository extends CrudRepository<Customers, Long> {

}
