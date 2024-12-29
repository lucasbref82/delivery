package br.com.delivery.v1.main.kitchen;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.service.KitchenService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class CreateKitchenMain {
    public static void main(String[] args) {
        var applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var kitchenService = applicationContext.getBean(KitchenService.class);
        var brasileira = new Kitchen();
        brasileira.setName("Brasileira");
        brasileira.setId(1L);
        var japonesa = new Kitchen();
        japonesa.setName("Japonesa");
        kitchenService.saveAll(List.of(brasileira, japonesa));

    }
}
