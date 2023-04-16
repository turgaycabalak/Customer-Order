package com.cabalak.backendchallenge.service;

import com.cabalak.backendchallenge.dto.orderDto.OrderDtoConverter;
import com.cabalak.backendchallenge.dto.orderDto.OrderRequestDto;
import com.cabalak.backendchallenge.dto.orderDto.OrderResponseDto;
import com.cabalak.backendchallenge.dto.orderDto.OrderWithCustomerResponseDto;
import com.cabalak.backendchallenge.exceptions.NotFoundException;
import com.cabalak.backendchallenge.model.Customer;
import com.cabalak.backendchallenge.model.Order;
import com.cabalak.backendchallenge.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderDtoConverter orderDtoConverter;

    protected Order findOrderById(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order not found : " + orderId));
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Customer customer = customerService.getUserById(orderRequestDto.customerId());//it throws exception if no found
        Order order = orderDtoConverter.convertToOrder(orderRequestDto, customer);
        Order savedOrder = orderRepository.save(order);

        return orderDtoConverter.convertToOrderResponseDto(savedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(String orderId) {
        boolean existed = orderRepository.existsById(orderId);
        if (!existed) throw new NotFoundException("Order not found : " + orderId);

        orderRepository.deleteById(orderId);
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrder(String orderId, OrderRequestDto orderRequestDto) {
        Order order = findOrderById(orderId);
//        order.setCreateDate(LocalDateTime.now());
        order.setTotalPrice(orderRequestDto.totalPrice());

        return orderDtoConverter.convertToOrderResponseDto(orderRepository.save(order));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orderListDb = orderRepository.findAll();

        return orderDtoConverter.convertToListOrderResponseDto(orderListDb);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<OrderResponseDto> getAllOrdersBySlice(Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(pageable);
        List<OrderResponseDto> orderResponseDtos = orderDtoConverter.convertToListOrderResponseDto(orderPage.getContent());

        return new PageImpl<>(orderResponseDtos, orderPage.getPageable(), orderPage.getTotalElements());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public OrderWithCustomerResponseDto getOrderById(String orderId) {
        Order order = findOrderById(orderId);

        return orderDtoConverter.convertToOrderWithCustomerResponseDto(order);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<OrderWithCustomerResponseDto> getAllOrdersAfterDate(LocalDateTime date) {
        List<Order> byCreateDateAfter = orderRepository.findByCreateDateAfter(date);

        return orderDtoConverter.convertToListOrderWithCustomerResponseDto(byCreateDateAfter);
    }
}
