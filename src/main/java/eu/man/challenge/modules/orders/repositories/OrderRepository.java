package eu.man.challenge.modules.orders.repositories;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;

import java.util.List;

public interface OrderRepository {
    OrderEntity getOrderById(String id);
    OrderEntity saveOrder(OrderEntity order);
    List<OrderEntity> getAll();
}
