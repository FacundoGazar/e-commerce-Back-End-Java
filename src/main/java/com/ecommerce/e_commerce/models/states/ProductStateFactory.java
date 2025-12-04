package com.ecommerce.e_commerce.models.states;

public class ProductStateFactory {
    public static ProductState fromString(String state) {
        if (state == null) return null;

        return switch (state) {
            case "PENDIENTE" -> new PendingProductState();
            case "CONFIRMADO" -> new ConfirmedProductState();
            case "ENVIADO" -> new ShippedProductState();
            case "ENTREGADO" -> new DeliveredProductState();
            case "CANCELADO" -> new CanceledProductState();
            default -> throw new IllegalArgumentException("Estado desconocido: " + state);
        };
    }

    public static String toString(ProductState state) {
        return switch (state) {
            case PendingProductState s -> "PENDIENTE";
            case ConfirmedProductState s -> "CONFIRMADO";
            case ShippedProductState s -> "ENVIADO";
            case DeliveredProductState s -> "ENTREGADO";
            case CanceledProductState s -> "CANCELADO";
            default -> throw new IllegalArgumentException("No conocemos esa clase de estado");
        };
    }
}
