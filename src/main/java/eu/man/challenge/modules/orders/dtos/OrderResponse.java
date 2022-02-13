package eu.man.challenge.modules.orders.dtos;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import org.springframework.http.HttpStatus;

public class OrderResponse {
	private final OrderEntity order;
	private final HttpStatus status;

	public OrderResponse(OrderEntity order, HttpStatus status) {
		super();
		this.order = order;
		this.status = status;
	}
	
	public OrderEntity getOrder() {
		return order;
	}
	public HttpStatus getStatus() {
		return status;
	}
}
