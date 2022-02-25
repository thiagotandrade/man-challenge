package eu.man.challenge.modules.orders.infra.http;

import java.util.List;

import eu.man.challenge.shared.entities.Order;
import eu.man.challenge.modules.orders.dtos.OrderResponse;
import eu.man.challenge.modules.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        Assert.notNull(orderService, "orderService must not be null!");
        this.orderService = orderService;
    }

    @GetMapping("/order/all")
    List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    Order getOrderById(@PathVariable("id") String id) {
    	return orderService.getOrderById(id);
    }

    @PostMapping("/order")
    @ResponseStatus(code = HttpStatus.CREATED)
    Order createOrder(@RequestBody Order order) {
       	return orderService.createOrder(order);
    }
}
