package eu.man.challenge.modules.kitchen.services.fakes;

import eu.man.challenge.modules.kitchen.services.KitchenService;
import eu.man.challenge.modules.orders.infra.entities.OrderEntity;

import java.util.List;

public class FakeKitchenService implements KitchenService {
    @Override
    public OrderEntity getOrderById(String id) {
        return null;
    }

    @Override
    public OrderEntity saveOrder(OrderEntity newOrder) {
        return null;
    }

    @Override
    public List<OrderEntity> getAll() {
        return null;
    }
}
