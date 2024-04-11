package com.example.spring_20240409;

import com.example.spring_20240409.entities.Laptop;
import com.example.spring_20240409.services.LaptopService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Scanner;

@AllArgsConstructor
@SpringBootApplication
public class Spring20240409Application {
    private LaptopService laptopService;

    public static void main(String[] args) {
        SpringApplication.run(Spring20240409Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart() {
        this.laptopService.addTestLaptops();
        this.laptopService.printAllLaptops();
        this.laptopService.printLaptopsByCompany("Dell");


        System.out.println("********************************************************************");
        boolean run = true;
        int recordsInPage = 5;
        int currentPageNum = 0;


        Scanner sc = new Scanner(System.in);

        while (run) {

            Pageable pageable = PageRequest.of(currentPageNum, recordsInPage);
            Page<Laptop> page = this.laptopService.getLaptopPage(pageable);
            this.laptopService.printLaptopsPage(page);

            System.out.println("Navigate pages with < and >. Close window using X");

            switch (sc.nextLine().toUpperCase()) {
                case ">" -> {
                    if (currentPageNum < page.getTotalPages() - 1) {
                        currentPageNum++;
                    } else {
                        System.out.println("Last page.");
                    }
                }
                case "<" -> {
                    if (currentPageNum > 0) {
                        currentPageNum--;
                    } else {
                        System.out.println("First page.");
                    }
                }
                case "X" -> run = false;
                default -> run = false;
            }
        }
        sc.close();
    }

}
