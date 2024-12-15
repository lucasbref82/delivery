package br.com.delivery.v1.service;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.domain.entity.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class CreateKitchenMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenService kitchenService = applicationContext.getBean(KitchenService.class);
        Kitchen brasileira = new Kitchen();
        brasileira.setName("Brasileira");
        Kitchen japonesa  = new Kitchen();
        japonesa.setName("Japonesa");
        kitchenService.saveAll(List.of(brasileira, japonesa));

    }
}
