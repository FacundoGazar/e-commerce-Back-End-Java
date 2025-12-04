package com.ecommerce.e_commerce.models.states;

import com.ecommerce.e_commerce.models.entities.Orders;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmedProductState implements ProductState {
    public void nextState(Orders orders) {
        orders.setState("ENVIADO");
    }

    public String toString() {
        return "CONFIRMADO";
    }
}
