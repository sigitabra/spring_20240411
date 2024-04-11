package com.example.spring_20240409.Services;

import com.example.spring_20240409.entities.Laptop;
import com.example.spring_20240409.repositories.LaptopRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class LaptopService {
    private LaptopRepository laptopRepository;
    public static final String LINE = "--------------------------------------------------------------------";
    public void addTestLaptops() {
        List<Laptop> laptops = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            Laptop laptop = new Laptop();
            laptop.setModel("Model-" + i);
            if (i % 5 == 0) {
                laptop.setCompany("Dell");
            } else if (i % 2 == 0) {
                laptop.setCompany("Apple");
            } else {
                laptop.setCompany("Hp");
            }
            laptop.setProcessor("Processor-" + i);
            laptop.setMemoryGB(i + 1);
            laptop.setPrice(i * 200);
            laptops.add(laptop);
        }
        this.laptopRepository.saveAllAndFlush(laptops);
    }

    public void printAllLaptops() {
        System.out.println("In total we have " + this.laptopRepository.count() + " laptops");
        System.out.println("Laptop with ID=15 exists: " + this.laptopRepository.existsById(15L));
        System.out.println(this.laptopRepository.findById(15L));
//        this.laptopRepository.deleteById(9L);
//        this.laptopRepository.deleteById(10L);
//        this.laptopRepository.deleteById(11L);
//        this.laptopRepository.deleteById(12L);
//        this.laptopRepository.deleteById(13L);
        System.out.println("Laptop with ID=15 exists: " + this.laptopRepository.existsById(15L));
        for (Laptop l : this.laptopRepository.findAll()) {
            System.out.println(l);
        }
    }

    public void printLaptopsByCompany(String comp) {
        for (Laptop l : this.laptopRepository.getAllByCompany(comp)) {
            System.out.println(l);
        }
    }

    public int getNumOfPages(Pageable pageable) {
        Page<Laptop> page = this.laptopRepository.findAll(pageable);
        return page.getTotalPages();
    }

    public static String getFormat(int curr, int total){
        if (curr==0){
            return "%50s %2s out of %2s [->] %n";
        } else if(curr==total-1){
            return "%50s [<-] %2s out of %2s %n";
        } else{
            return"%45s [<-] %2s out of %2s [->] %n";
        }

    }
    public void printLaptopsPage(Pageable pageable) {
        int totalPages=getNumOfPages(pageable);
        System.out.printf(getFormat(pageable.getPageNumber(),totalPages), "", pageable.getPageNumber() + 1, totalPages);
        System.out.println(LINE);
        System.out.printf(Laptop.FORMATAS + "%n", "Company", "Model", "Procesor", "Memory", "Price");
        System.out.println(LINE);
        for (Laptop l : this.laptopRepository.findAll(pageable)) {
            System.out.println(l);
        }
        System.out.println(LINE);
        System.out.printf(getFormat(pageable.getPageNumber(),totalPages), "", pageable.getPageNumber() + 1, totalPages);
    }
}
