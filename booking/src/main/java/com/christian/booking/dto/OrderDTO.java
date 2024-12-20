package com.christian.booking.dto;

import com.christian.booking.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private List<OrderItem> orderItems;
}
