package eu.man.challenge.modules.orders.dtos;

import eu.man.challenge.shared.entities.Order;
import org.springframework.http.HttpStatus;

public class OrderResponse {
	private final Order order;
	private final HttpStatus status;

	public OrderResponse(Order order, HttpStatus status) {
		this.order = order;
		this.status = status;
	}
	
	public Order getOrder() {
		return order;
	}
	public HttpStatus getStatus() {
		return status;
	}
}
