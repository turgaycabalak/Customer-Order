package com.cabalak.backendchallenge;

import com.cabalak.backendchallenge.model.Customer;
import com.cabalak.backendchallenge.model.Order;
import com.cabalak.backendchallenge.repository.CustomerRepository;
import com.cabalak.backendchallenge.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class BackendChallengeApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public BackendChallengeApplication(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.saveAll(List.of(
                new Customer("Turgay", 30),
                new Customer("Ayaz", 10),
                new Customer("Nehir", 14),
                new Customer("Olcay", 33),
                new Customer("Rıza", 59),
                new Customer("Mahire", 53),
                new Customer("İbrahim", 40)
        ));
        List<Customer> customers = customerRepository.findAll();

        orderRepository.saveAll(List.of(
                new Order(LocalDateTime.now(), BigDecimal.valueOf(50000), customers.get(2)),
                new Order(LocalDateTime.now().plusDays(3), BigDecimal.valueOf(40000), customers.get(2)),
                new Order(LocalDateTime.now().plusDays(15), BigDecimal.valueOf(15000000), customers.get(1)),
                new Order(LocalDateTime.now().plusDays(32), BigDecimal.valueOf(200000), customers.get(2)),
                new Order(LocalDateTime.now().plusDays(45), BigDecimal.valueOf(85000000), customers.get(2)),
                new Order(LocalDateTime.now().plusDays(55), BigDecimal.valueOf(6000), customers.get(4)),
                new Order(LocalDateTime.now().plusDays(62), BigDecimal.valueOf(50000), customers.get(5)),
                new Order(LocalDateTime.now().plusDays(80), BigDecimal.valueOf(4465000), customers.get(3)),
                new Order(LocalDateTime.now().plusDays(102), BigDecimal.valueOf(2000), customers.get(4)),
                new Order(LocalDateTime.now().plusDays(200), BigDecimal.valueOf(500500050), customers.get(5)),
                new Order(LocalDateTime.now().plusDays(250), BigDecimal.valueOf(450450), customers.get(5))
        ));
    }
}
