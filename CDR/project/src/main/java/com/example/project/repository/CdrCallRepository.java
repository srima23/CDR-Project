package com.example.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.project.entity.CdrCall;

public interface CdrCallRepository extends CrudRepository<CdrCall, Long> {

}
