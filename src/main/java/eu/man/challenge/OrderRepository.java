package eu.man.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
	@Autowired
	private KitchenService kitchenService;
	
	public OrderEntity getOrderById(String id) {
		return kitchenService.getOrderById(id);
	}
	
	public Boolean saveOrder(OrderEntity order) {
		return kitchenService.saveOrder(order);
	}

	public List<OrderEntity> getAll() {
		return kitchenService.getAll();
	}
}
