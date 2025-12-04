package com.ecommerce.e_commerce.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersRequest {
    private Integer userId;
    private String state;
    private List<OrdersDetailRequest> orderDetail;
}
