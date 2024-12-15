package br.com.delivery.v1.service;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.domain.entity.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class CreateKitchenMain {
    public static void main(String[] args) {
        var applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var kitchenService = applicationContext.getBean(KitchenService.class);
        var brasileira = Kitchen.builder().name("Brasileira").build();
        var japonesa = Kitchen.builder().name("Japonesa").build();
        kitchenService.saveAll(List.of(brasileira, japonesa));

    }
}
