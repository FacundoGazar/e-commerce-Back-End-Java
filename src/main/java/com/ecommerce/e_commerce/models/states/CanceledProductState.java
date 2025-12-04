package com.ecommerce.e_commerce.models.states;

import com.ecommerce.e_commerce.models.entities.Orders;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CanceledProductState implements ProductState {
    public void nextState(Orders orders) {

    }

    public String toString() {
        return "CANCELADO";
    }
}
