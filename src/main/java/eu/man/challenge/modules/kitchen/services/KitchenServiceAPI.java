package eu.man.challenge.modules.kitchen.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;

/*
	We assume that the KitchenServiceAPI is an external API, so we don't have any control over it.
	It returns null when an order was not found or an order with same id exists.
 */

public class KitchenServiceAPI {
	private final List<OrderEntity> orders;

	public KitchenServiceAPI() {
		this.orders = new ArrayList<>();
	}

	public OrderEntity getOrderById(String id) {
		for(OrderEntity order : this.orders) {
			if (order.getId().equals(id)) {
				return order;
			}
		}

		return null;
	}

	public OrderEntity saveOrder(OrderEntity newOrder) {
		newOrder.setId(UUID.randomUUID().toString());

		Optional<OrderEntity> existingOrder = this.orders.stream()
				.filter(o -> newOrder.getId().equals(o.getId()))
				.findFirst();

		if (existingOrder.isPresent()) {
			return null;
		}

		orders.add(newOrder);
		return newOrder;
	}

	public List<OrderEntity> getAll() {
		return this.orders;
	}
}
