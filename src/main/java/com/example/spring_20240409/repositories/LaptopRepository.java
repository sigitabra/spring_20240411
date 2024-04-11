package com.example.spring_20240409.repositories;

import com.example.spring_20240409.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> getAllByCompany(String company);

}

