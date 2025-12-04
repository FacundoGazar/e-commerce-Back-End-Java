package com.ecommerce.e_commerce.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdersDetailRequest {
    private Integer idProduct;
    private int amount;
}
