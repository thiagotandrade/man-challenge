package eu.man.challenge.modules.kitchen.services;

import eu.man.challenge.modules.orders.infra.entities.NullOrder;
import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/*
    Due to the KitchenServiceAPI returning null in some cases, nullity checks are needed.
    If an order is null, we apply the special case pattern to represent the null order.
    This assures that there won't be NullPointerExceptions within our application.
 */

public class KitchenServiceAdapter implements KitchenService {
    private final KitchenServiceAPI kitchenService;

    @Autowired
    public KitchenServiceAdapter(KitchenServiceAPI kitchenService) {
        Assert.notNull(kitchenService, "kitchenService must not be null!");
        this.kitchenService = kitchenService;
    }

    @Override
    public OrderEntity getOrderById(String id) {
        OrderEntity order = this.kitchenService.getOrderById(id);

        if(Objects.isNull(order)) {
            return new NullOrder();
        }

        return order;
    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        OrderEntity savedOrder = this.kitchenService.saveOrder(order);

        if(Objects.isNull(savedOrder)) {
            return new NullOrder();
        }

        return order;
    }

    @Override
    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = this.kitchenService.getAll();

        if(orders == null || orders.isEmpty()) {
            return Collections.emptyList();
        }

        return orders;
    }
}
