package eu.man.challenge.modules.orders.service;

import eu.man.challenge.shared.entities.Order;
import eu.man.challenge.shared.exceptions.InvalidOrderException;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

public class OrderFieldsValidator {

    public static void validate(Order order) {
        validateBlankString(order.getCustomer());
        validateEmptyList(order.getIngredients());

        for(String ingredient : order.getIngredients()) {
            validateBlankString(ingredient);
            if(ingredient.toLowerCase().contains("p")) {
                throw new InvalidOrderException("The given order contains invalid ingredient(s)");
            }
        }
    }

    private static void validateBlankString(String customer) {
        if(Objects.isNull(customer) || customer.isBlank()) {
            throw new InvalidOrderException("The order must not have empty customer nor ingredient");
        }
    }

    private static void validateEmptyList(List<String> items) {
        if(ObjectUtils.isEmpty(items)) {
            throw new InvalidOrderException("Missing ingredients parameter");
        }
    }
}
