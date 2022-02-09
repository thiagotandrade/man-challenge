package eu.man.challenge.modules.kitchen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {
	private final List<OrderEntity> orders;

	public KitchenService() {
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
