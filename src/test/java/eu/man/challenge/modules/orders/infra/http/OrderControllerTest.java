package eu.man.challenge.modules.orders.infra.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.man.challenge.modules.kitchen.service.KitchenService;
import eu.man.challenge.shared.entities.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private KitchenService kitchenService;

    @Test
    void should_ReturnOrder_When_OrderListIdsContainsProvidedId() throws Exception {
        Order order = new Order("customer-name", Arrays.asList("ingredient-1", "ingredient-2", "ingredient-3"));
        this.kitchenService.saveOrder(order);

        mvc.perform(get("/order/{id}", order.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.customer").value(order.getCustomer()))
                .andExpect(jsonPath("$.ingredients[0]").value(order.getIngredients().get(0)))
                .andExpect(jsonPath("$.ingredients[1]").value(order.getIngredients().get(1)))
                .andExpect(jsonPath("$.ingredients[2]").value(order.getIngredients().get(2)));
    }

    @Test
    void should_ThrowOrderNotFoundException_When_OrderListIdsDoesNotHaveProvidedId() throws Exception {
        String nonExistentId = "mock-id";
        mvc.perform(get("/order/{id}", nonExistentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Order not found with id " + nonExistentId))
                .andExpect(jsonPath("$.status").value("error"));
    }

    @Test
    void should_ReturnOrderList_When_OrderListHasItems() throws Exception {
        Order order1 = new Order("customer1", Arrays.asList("order-1-ingredient-1", "order-1-ingredient-2", "order-1-ingredient-3"));
        this.kitchenService.saveOrder(order1);

        Order order2 = new Order("customer2", Arrays.asList("order-2-ingredient-1", "order-2-ingredient-2", "order-2-ingredient-3"));
        this.kitchenService.saveOrder(order2);

        mvc.perform(get("/order/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customer").value("customer1"))
                .andExpect(jsonPath("$[0].ingredients[0]").value(order1.getIngredients().get(0)))
                .andExpect(jsonPath("$[0].ingredients[1]").value(order1.getIngredients().get(1)))
                .andExpect(jsonPath("$[0].ingredients[2]").value(order1.getIngredients().get(2)))
                .andExpect(jsonPath("$[1].customer").value("customer2"))
                .andExpect(jsonPath("$[1].ingredients[0]").value(order2.getIngredients().get(0)))
                .andExpect(jsonPath("$[1].ingredients[1]").value(order2.getIngredients().get(1)))
                .andExpect(jsonPath("$[1].ingredients[2]").value(order2.getIngredients().get(2)));
    }

    @Test
    void should_ReturnEmptyList_When_OrderListHasNoItems() throws Exception {
        mvc.perform(get("/order/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    void should_ReturnOrder_When_CreateOrderSuccessfully() throws Exception {
        String customerName = "customer-name";
        List<String> ingredients = Arrays.asList("ingredient-1", "ingredient-2", "ingredient-3");
        Order order = new Order(customerName, ingredients);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        mvc.perform(post("/order")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customer").value(order.getCustomer()))
                .andExpect(jsonPath("$.ingredients[0]").value(order.getIngredients().get(0)))
                .andExpect(jsonPath("$.ingredients[1]").value(order.getIngredients().get(1)))
                .andExpect(jsonPath("$.ingredients[2]").value(order.getIngredients().get(2)));
    }

    @ParameterizedTest
    @MethodSource("eu.man.challenge.modules.orders.service.OrderServiceTest#invalidIngredients")
    void should_ThrowInvalidOrderException_When_WhileCreatingOrderIngredientsAreBlankOrHaveLetterP(List<String> ingredients) throws Exception {
        String customerName = "customer-name";
        Order order = new Order(customerName, ingredients);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        mvc.perform(post("/order")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"));
    }

    @ParameterizedTest
    @MethodSource("eu.man.challenge.modules.orders.service.OrderServiceTest#blankStrings")
    void should_ThrowInvalidOrderException_When_OrderCustomerIsEmpty(String customerName) throws Exception {
        Order order = new Order(customerName, Arrays.asList("ingredient-1", "ingredient-2", "ingredient-3"));

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        mvc.perform(post("/order")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The order must not have empty customer nor ingredient"))
                .andExpect(jsonPath("$.status").value("error"));
    }
}
