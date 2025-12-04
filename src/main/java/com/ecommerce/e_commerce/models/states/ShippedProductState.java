package com.ecommerce.e_commerce.models.states;

import com.ecommerce.e_commerce.models.entities.Orders;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippedProductState implements ProductState {
    public void nextState(Orders orders) {
        orders.setState("ENTREGADO");
    }

    public String toString() {
        return "ENVIADO";
    }
}
