package eu.man.challenge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    
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
