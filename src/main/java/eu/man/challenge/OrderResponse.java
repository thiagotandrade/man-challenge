package eu.man.challenge;

import org.springframework.http.HttpStatus;

public class OrderResponse {
	private OrderEntity order;
	private HttpStatus status;

	public OrderResponse(OrderEntity order, HttpStatus status) {
		super();
		this.order = order;
		this.status = status;
	}
	
	public OrderEntity getOrder() {
		return order;
	}
	public void setOrder(OrderEntity order) {
		this.order = order;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
}
