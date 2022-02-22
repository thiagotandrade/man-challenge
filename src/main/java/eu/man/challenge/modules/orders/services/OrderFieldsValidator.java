package eu.man.challenge.modules.orders.services;

import eu.man.challenge.shared.entities.OrderEntity;
import eu.man.challenge.shared.exceptions.InvalidOrderException;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

public class OrderFieldsValidator {

    public static void validate(OrderEntity order) {
        if (Objects.isNull(order.getCustomer()) || ObjectUtils.isEmpty(order.getIngredients())) {
            throw new InvalidOrderException("Mandatory information is missing for the given order");
        }

        for(String ingredient : order.getIngredients()) {
            if(ingredient.toLowerCase().contains("p")) {
                throw new InvalidOrderException("The given order contains invalid ingredient(s)");
            }
        }
    }
}
