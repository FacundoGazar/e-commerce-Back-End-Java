package com.ecommerce.e_commerce.models.states;

import com.ecommerce.e_commerce.models.entities.Orders;

public interface ProductState {
    void nextState(Orders orders);
}
