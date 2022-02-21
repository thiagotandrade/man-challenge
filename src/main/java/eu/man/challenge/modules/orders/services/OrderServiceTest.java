package eu.man.challenge.modules.orders.services;

import eu.man.challenge.modules.kitchen.services.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

public class OrderServiceTest {
// TODO: use mockito (mockBean annotation) to create mocks for the kitchen service
    @Autowired
    private OrderService orderService;

    @MockBean
    private KitchenService kitchenService;



    // Unit test the orderService methods, with a fake implementation of the kitchenService
    // should create a new order
    // should throw exception when order ingredients contain the letter p
    // should throw exception when order id is already in orders
    // should get existing order by id
    // should throw exception when order not found
}
