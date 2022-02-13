package eu.man.challenge.modules.orders.repositories;

import java.util.List;

import eu.man.challenge.modules.kitchen.services.KitchenService;
import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	private final KitchenService kitchenService;

	@Autowired
	public OrderRepositoryImpl(KitchenService kitchenService) {
		Assert.notNull(kitchenService, "kitchenService must not be null!");
		this.kitchenService = kitchenService;
	}

	@Override
	public OrderEntity getOrderById(String id) {
		return kitchenService.getOrderById(id);
	}

	@Override
	public OrderEntity saveOrder(OrderEntity order) {
		return kitchenService.saveOrder(order);
	}

	@Override
	public List<OrderEntity> getAll() {
		return kitchenService.getAll();
	}
}
