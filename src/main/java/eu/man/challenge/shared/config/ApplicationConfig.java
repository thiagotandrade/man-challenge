package eu.man.challenge.shared.config;

import eu.man.challenge.modules.kitchen.services.KitchenService;
import eu.man.challenge.modules.kitchen.services.KitchenServiceAdapter;
import eu.man.challenge.modules.kitchen.services.KitchenServiceAPI;
import eu.man.challenge.modules.orders.repositories.OrderRepository;
import eu.man.challenge.modules.orders.repositories.OrderRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("eu.man.challenge")
public class ApplicationConfig  {

    @Bean
    public KitchenService kitchenService() {
        return new KitchenServiceAdapter(new KitchenServiceAPI());
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepositoryImpl(kitchenService());
    }
}
