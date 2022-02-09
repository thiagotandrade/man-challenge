package eu.man.challenge.modules.orders.services;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import eu.man.challenge.modules.orders.dtos.OrderResponse;
import eu.man.challenge.modules.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderResponse getOrderById(String id) {
		OrderEntity orderFound = orderRepository.getOrderById(id);

		if(Objects.nonNull(orderFound)) {
			return new OrderResponse(orderFound, HttpStatus.OK);
		}
		
		return new OrderResponse(null, HttpStatus.NOT_FOUND);
	}
	
	public OrderResponse createOrder(OrderEntity order) {
		for(String ingredient : order.getIngredients()) {
			if(ingredient.toLowerCase().contains("p")) {
				return new OrderResponse(null, HttpStatus.PRECONDITION_FAILED);
			}
		}

		OrderEntity returnedOrder = orderRepository.saveOrder(order);
		if(Objects.isNull(returnedOrder)) {
			return new OrderResponse(null, HttpStatus.CONFLICT);
		}

		return new OrderResponse(returnedOrder, HttpStatus.OK);
	}
	
	public List<OrderEntity> getAllOrders() {
		return orderRepository.getAll();
	}
}
