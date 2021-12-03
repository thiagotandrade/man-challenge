package eu.man.challenge;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		order.setId(UUID.randomUUID().toString());
		
		if(orderRepository.saveOrder(order).booleanValue()) {
			return new OrderResponse(order, HttpStatus.OK);
		}
		
		return new OrderResponse(null, HttpStatus.CONFLICT);
	}
	
	public List<OrderEntity> getAllOrders() {
		return orderRepository.getAll();
	}
}
