package com.cabalak.backendchallenge.controller;

import com.cabalak.backendchallenge.dto.orderDto.OrderRequestDto;
import com.cabalak.backendchallenge.genericResponse.SuccessDataResult;
import com.cabalak.backendchallenge.genericResponse.SuccessResult;
import com.cabalak.backendchallenge.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.createOrder(orderRequestDto), "Order created."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("id") String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(new SuccessResult("Order successfully deleted. Order Id : " + orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable("id") String orderId, @Valid @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.updateOrder(orderId, orderRequestDto), "Order successfully updated."));
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.getAllOrders(), "All orders fetched successfully."));
    }

    @GetMapping("/slice")
    public ResponseEntity<?> getAllOrdersBySlice(Pageable pageable) {
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.getAllOrdersBySlice(pageable), "Orders fetched successfully by pagination."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") String orderId) {
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.getOrderById(orderId), "Order found by id : " + orderId));
    }

    @GetMapping("/created-after")//TODO: pageable olarak yap
    public ResponseEntity<?> getAllOrdersAfterDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime date) {
        return ResponseEntity.ok(new SuccessDataResult<>(orderService.getAllOrdersAfterDate(date), "Orders after " + date + " listed."));
    }


}
