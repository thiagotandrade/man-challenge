package eu.man.challenge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class KitchenService {
	private List<OrderEntity> orders;

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
	
	public Boolean saveOrder(OrderEntity newOrder) {
		for(OrderEntity o : this.orders) {
			if (o.getId().equals(newOrder.getId())) {
				return false;
			}
		}		
		
		orders.add(newOrder);
		return true;
	}

	public List<OrderEntity> getAll() {
		return this.orders;
	}
}
