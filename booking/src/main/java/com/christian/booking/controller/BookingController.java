package com.christian.booking.controller;

import com.christian.booking.client.StockClient;
import com.christian.booking.dto.OrderDTO;
import com.christian.booking.entity.Order;
import com.christian.booking.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping("order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDTO orderDTO){

        boolean inStock = orderDTO.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

        if(inStock){
            Order order = new Order();

            order.setOrderNo(UUID.randomUUID().toString());
            order.setItems(orderDTO.getOrderItems());

            orderRepository.save(order);
            return "Order saved";
        }



        return "Se chingo la huevada";
    }

    private String fallbackToStockService() {
        return "Somithing went wrong, please try after some time";
    }
}
