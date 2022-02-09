package eu.man.challenge.modules.orders.repositories;

import java.util.List;

import eu.man.challenge.modules.kitchen.KitchenService;
import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
	@Autowired
	private KitchenService kitchenService;
	
	public OrderEntity getOrderById(String id) {
		return kitchenService.getOrderById(id);
	}
	
	public OrderEntity saveOrder(OrderEntity order) {
		return kitchenService.saveOrder(order);
	}

	public List<OrderEntity> getAll() {
		return kitchenService.getAll();
	}
}
