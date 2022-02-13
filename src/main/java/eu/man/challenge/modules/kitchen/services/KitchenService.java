package eu.man.challenge.modules.kitchen.services;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;

import java.util.List;

public interface KitchenService {
    OrderEntity getOrderById(String id);
    OrderEntity saveOrder(OrderEntity newOrder);
    List<OrderEntity> getAll();
}
