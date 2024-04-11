package com.example.spring_20240409.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    private double price;

    private String company;

    private String processor;

    private int memoryGB;

    public static final String FORMATAS = " %-10s | %-10s | %-15s | %6s Gb| %8s Eur";

    @Override
    public String toString() {
        return String.format(Laptop.FORMATAS, getCompany(), getModel(), getProcessor(), getMemoryGB(), getPrice());
    }
}
