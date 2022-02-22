package eu.man.challenge.modules.orders.infra.http;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderControllerTest {
    // Integration tests. Test all controllers endpoints without mocks.
    // createOrder: successfully create order; exception when ingredients contain letter p; exception when id already existis
    // getAll: get all orders
    // getById: successfully get order; exception when order not found
}
