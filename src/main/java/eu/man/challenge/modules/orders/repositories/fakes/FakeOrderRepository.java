package eu.man.challenge.modules.orders.repositories.fakes;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import eu.man.challenge.modules.orders.repositories.OrderRepository;

import java.util.List;

public class FakeOrderRepository implements OrderRepository {

    @Override
    public OrderEntity getOrderById(String id) {
        return null;
    }

    @Override
    public OrderEntity saveOrder(OrderEntity order) {
        return null;
    }

    @Override
    public List<OrderEntity> getAll() {
        return null;
    }
}
