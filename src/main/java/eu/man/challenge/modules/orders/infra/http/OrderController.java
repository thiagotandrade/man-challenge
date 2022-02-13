package eu.man.challenge.modules.orders.infra.http;

import java.util.List;

import eu.man.challenge.modules.orders.infra.entities.OrderEntity;
import eu.man.challenge.modules.orders.dtos.OrderResponse;
import eu.man.challenge.modules.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        Assert.notNull(orderService, "orderService must not be null!");
        this.orderService = orderService;
    }


    @GetMapping("/order/all")
    public ResponseEntity<List<OrderEntity>> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable String id) {
    	OrderResponse response = orderService.getOrderById(id);
		return new ResponseEntity<>(response.getOrder(), response.getStatus());
    }

    @PostMapping("/order")
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
       	OrderResponse response = orderService.createOrder(order);
		return new ResponseEntity<>(response.getOrder(), response.getStatus());
    }
}
