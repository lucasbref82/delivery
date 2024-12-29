package br.com.delivery.v1.main.kitchen;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.domain.service.KitchenService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class FindKitchenMain {

    public static void main(String[] args) {

        /**
         * Cria configura e incia  a aplicação, definindo que não é web
         * aproveitando os beans escaneados da classe que tem a notação @SpringBootApplication
         */

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var kitchenService = applicationContext.getBean(KitchenService.class);
        kitchenService.findAll().blockingGet().forEach(c -> System.out.println(c.getName()));

    }
}
