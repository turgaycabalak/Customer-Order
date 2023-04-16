package com.cabalak.backendchallenge.repository;

import com.cabalak.backendchallenge.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByCreateDateAfter(LocalDateTime date);

}
