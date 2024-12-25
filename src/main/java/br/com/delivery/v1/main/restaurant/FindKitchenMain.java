package br.com.delivery.v1.main.restaurant;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.domain.service.RestaurantService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class FindKitchenMain {
    public static void main(String[] args) {
        var applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var restaurantService = applicationContext.getBean(RestaurantService.class);

        restaurantService.findAll().blockingGet().forEach(System.out::println);
    }
}
