package eu.man.challenge.modules.orders.services;

import java.util.List;

import eu.man.challenge.modules.kitchen.services.KitchenService;
import eu.man.challenge.shared.entities.Order;
import eu.man.challenge.modules.orders.dtos.OrderResponse;
import eu.man.challenge.shared.exceptions.OrderAlreadyExistsException;
import eu.man.challenge.shared.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class OrderService {
	private final KitchenService kitchenService;

	@Autowired
	public OrderService(KitchenService kitchenService) {
		Assert.notNull(kitchenService, "kitchenService must not be null!");
		this.kitchenService = kitchenService;
	}

	public OrderResponse getOrderById(String id) {
		Order order = kitchenService.getOrderById(id);

		if(order.isNull()) {
			throw new OrderNotFoundException("Order not found with id " + id);
		}
		
		return new OrderResponse(order, HttpStatus.OK);
	}
	
	public OrderResponse createOrder(Order order) {
		OrderFieldsValidator.validate(order);

		Order returnedOrder = kitchenService.saveOrder(order);
		if(returnedOrder.isNull()) {
			throw new OrderAlreadyExistsException("An order with id " + order.getId() + "already exists");
		}

		return new OrderResponse(returnedOrder, HttpStatus.OK);
	}
	
	public List<Order> getAllOrders() {
		return kitchenService.getAll();
	}
}
