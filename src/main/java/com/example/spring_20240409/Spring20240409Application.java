package com.example.spring_20240409;

import com.example.spring_20240409.Services.LaptopService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

        Boolean run = true;
        int recordsInPage = 5;
        int currentPageNum = 0;
        Pageable firstPage=PageRequest.of(0, recordsInPage);
        int numOfpages=this.laptopService.getNumOfPages(firstPage);
        Scanner sc = new Scanner(System.in);

        this.laptopService.printLaptopsPage(firstPage);

        while (run) {
            System.out.println("Navigate pages with < and >. Close window using X");
            switch (sc.nextLine().toUpperCase()) {
                case ">" -> {
                    if (currentPageNum < numOfpages - 1) {
                        this.laptopService.printLaptopsPage(PageRequest.of(++currentPageNum, recordsInPage));
                    } else {
                        System.out.println("Last page.");
                    }
                }
                case "<" -> {
                    if (currentPageNum > 0) {
                        this.laptopService.printLaptopsPage(PageRequest.of(--currentPageNum, recordsInPage));
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
