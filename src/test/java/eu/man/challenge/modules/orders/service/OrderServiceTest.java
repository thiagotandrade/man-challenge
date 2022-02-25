package eu.man.challenge.modules.orders.service;

import eu.man.challenge.modules.kitchen.service.KitchenService;
import eu.man.challenge.shared.entities.NullOrder;
import eu.man.challenge.shared.entities.Order;
import eu.man.challenge.shared.exceptions.InvalidOrderException;
import eu.man.challenge.shared.exceptions.OrderAlreadyExistsException;
import eu.man.challenge.shared.exceptions.OrderNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private KitchenService kitchenService;

    @Test
    void should_ReturnOrder_When_OrderIsValid() {
        Order order = createValidOrder();

        Mockito.when(kitchenService.saveOrder(any(Order.class))).thenReturn(order);
        Order savedOrder = orderService.createOrder(order);

        assertAll(
                () -> assertEquals(order.getId(), savedOrder.getId()),
                () -> assertEquals(order.getCustomer(), savedOrder.getCustomer()),
                () -> assertEquals(order.getIngredients(), savedOrder.getIngredients())
        );
    }

    private Order createValidOrder() {
        return new Order(
                "order-id",
                "customer-name",
                Arrays.asList("ingredient-1", "ingredient-2", "ingredient-3"));
    }

    @ParameterizedTest
    @MethodSource("blankStrings")
    void should_ThrowInvalidOrderException_When_OrderCustomerIsEmpty(String customerName) {
        Order order = new Order(customerName, Arrays.asList("ingredient-1", "ingredient-2", "ingredient-3"));

        Executable executable = () -> orderService.createOrder(order);
        assertThrows(InvalidOrderException.class, executable);
    }

    private static Stream<String> blankStrings() {
        return Stream.of("", "   ", null);
    }

    @ParameterizedTest
    @MethodSource("invalidIngredients")
    void should_ThrowInvalidOrderException_When_OrderIngredientsAreBlankOrHaveLetterP(List<String> ingredients) {
        Order order = new Order("customer-name", ingredients);

        Executable executable = () -> orderService.createOrder(order);
        assertThrows(InvalidOrderException.class, executable);
    }

    static Stream<List<String>> invalidIngredients() {
        return Stream.of(
                Arrays.asList("ingredient-1", "   ", "ingredient-3"),
                Arrays.asList("ingredient-1", "", "ingredient-3"),
                Arrays.asList("ingredient-1", null, "ingredient-3"),
                Arrays.asList("ingredient-1", "pistachio", "ingredient-3"),
                Arrays.asList("ingredient-1", "Parmesan cheese", "ingredient-3"),
                Collections.emptyList()
        );
    }

    @Test
    void should_ThrowOrderAlreadyExistsException_When_KitchenServiceReturnsNullOrder() {
        Order order = createValidOrder();

        Mockito.when(kitchenService.saveOrder(any(Order.class))).thenReturn(new NullOrder());
        Executable executable = () -> orderService.createOrder(order);

        assertThrows(OrderAlreadyExistsException.class, executable);
    }

    @Test
    void should_ReturnOrder_When_IdWasFoundInOrderList() {
        Order order = createValidOrder();

        Mockito.when(kitchenService.getOrderById(any(String.class))).thenReturn(order);
        Order foundOrder = orderService.getOrderById(order.getId());

        assertAll(
                () -> assertEquals(order.getId(), foundOrder.getId()),
                () -> assertEquals(order.getCustomer(), foundOrder.getCustomer()),
                () -> assertEquals(order.getIngredients(), foundOrder.getIngredients())
        );
    }

    @Test
    void should_ThrowOrderNotFoundException_When_IdWasNotFoundInOrderList() {
        String nonExistentId = "fake-id";

        Mockito.when(kitchenService.getOrderById(any(String.class))).thenReturn(new NullOrder());
        Executable executable = () -> orderService.getOrderById(nonExistentId);

        assertThrows(OrderNotFoundException.class, executable);
    }

    @Test
    void should_ReturnAllOrders_When_OrderListHasItems() {
        Order order1 = createOrderWithId("id-1");
        Order order2 = createOrderWithId("id-2");
        Order order3 = createOrderWithId("id-3");
        List<Order> orders = Arrays.asList(order1, order2, order3);

        Mockito.when(kitchenService.getAll()).thenReturn(orders);
        List<Order> foundOrders = orderService.getAllOrders();

        // then
        assertEquals(foundOrders, orders);
    }

    private Order createOrderWithId(String id) {
        Order order = createValidOrder();
        order.setId(id);

        return order;
    }

    @Test
    void should_ReturnEmptyList_When_OrderListIsEmpty() {
        List<Order> emptyList = new ArrayList<>();

        Mockito.when(kitchenService.getAll()).thenReturn(emptyList);
        List<Order> foundOrders = orderService.getAllOrders();

        assertEquals(foundOrders, emptyList);
    }
}
