package br.com.delivery.v1.service;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.domain.entity.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeleteKitchenMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var cozinhaService = applicationContext.getBean(KitchenService.class);

        Kitchen cozinha = new Kitchen();
        cozinha.setId(1L);
        cozinha.setName("Tailandesa");

        cozinhaService.deleteKitchen(cozinha);

    }
}