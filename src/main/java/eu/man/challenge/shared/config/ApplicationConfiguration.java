package eu.man.challenge.shared.config;

import eu.man.challenge.modules.kitchen.service.KitchenService;
import eu.man.challenge.modules.kitchen.service.KitchenServiceAdapter;
import eu.man.challenge.modules.kitchen.service.KitchenServiceAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("eu.man.challenge")
class ApplicationConfiguration {

    @Bean
    KitchenService kitchenService() {
        return new KitchenServiceAdapter(new KitchenServiceAPI());
    }
}
