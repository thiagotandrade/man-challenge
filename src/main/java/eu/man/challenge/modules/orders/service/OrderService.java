package eu.man.challenge.modules.orders.service;

import eu.man.challenge.modules.kitchen.service.KitchenService;
import eu.man.challenge.shared.entities.Order;
import eu.man.challenge.shared.exceptions.OrderAlreadyExistsException;
import eu.man.challenge.shared.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class OrderService {
	private final KitchenService kitchenService;

	@Autowired
	public OrderService(KitchenService kitchenService) {
		Assert.notNull(kitchenService, "kitchenService must not be null!");
		this.kitchenService = kitchenService;
	}

	public Order getOrderById(String id) {
		Order order = kitchenService.getOrderById(id);

		if(order.isNull()) {
			throw new OrderNotFoundException("Order not found with id " + id);
		}
		
		return order;
	}
	
	public Order createOrder(Order order) {
		OrderFieldsValidator.validate(order);

		Order returnedOrder = kitchenService.saveOrder(order);
		if(returnedOrder.isNull()) {
			throw new OrderAlreadyExistsException("An order with id " + order.getId() + "already exists");
		}

		return returnedOrder;
	}
	
	public List<Order> getAllOrders() {
		return kitchenService.getAll();
	}
}
