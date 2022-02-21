package eu.man.challenge.shared.config;

import eu.man.challenge.modules.kitchen.services.KitchenService;
import eu.man.challenge.modules.kitchen.services.KitchenServiceAdapter;
import eu.man.challenge.modules.kitchen.services.KitchenServiceAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("eu.man.challenge")
public class ApplicationConfiguration {

    @Bean
    public KitchenService kitchenService() {
        return new KitchenServiceAdapter(new KitchenServiceAPI());
    }
}
