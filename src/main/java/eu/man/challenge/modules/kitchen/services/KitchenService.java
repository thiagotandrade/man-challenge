package eu.man.challenge.modules.kitchen.services;

import eu.man.challenge.shared.entities.Order;

import java.util.List;

public interface KitchenService {
    Order getOrderById(String id);
    Order saveOrder(Order newOrder);
    List<Order> getAll();
}
