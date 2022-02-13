package eu.man.challenge.modules.orders.services;

import java.util.List;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import eu.man.challenge.modules.orders.dtos.OrderResponse;
import eu.man.challenge.modules.orders.repositories.OrderRepositoryImpl;
import eu.man.challenge.shared.exceptions.InvalidOrderException;
import eu.man.challenge.shared.exceptions.OrderAlreadyExistsException;
import eu.man.challenge.shared.exceptions.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepositoryImpl orderRepository;

	public OrderService(OrderRepositoryImpl orderRepository) {
		this.orderRepository = orderRepository;
	}

	public OrderResponse getOrderById(String id) {
		OrderEntity order = orderRepository.getOrderById(id);

		if(order.isNull()) {
			throw new OrderNotFoundException("Order not found with id " + id);
		}
		
		return new OrderResponse(order, HttpStatus.OK);
	}
	
	public OrderResponse createOrder(OrderEntity order) {
		for(String ingredient : order.getIngredients()) {
			if(ingredient.toLowerCase().contains("p")) {
				throw new InvalidOrderException("The given order contains invalid ingredients");
			}
		}

		OrderEntity returnedOrder = orderRepository.saveOrder(order);
		if(returnedOrder.isNull()) {
			throw new OrderAlreadyExistsException("An order with id " + order.getId() + "already exists");
		}

		return new OrderResponse(returnedOrder, HttpStatus.OK);
	}
	
	public List<OrderEntity> getAllOrders() {
		return orderRepository.getAll();
	}
}
