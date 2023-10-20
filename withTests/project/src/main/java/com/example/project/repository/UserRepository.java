package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.project.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByName(String name);

    public boolean existsByName(String name);

    public String findRoleByName(String username);

}