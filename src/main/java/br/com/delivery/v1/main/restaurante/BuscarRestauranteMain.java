package br.com.delivery.v1.main.restaurante;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.repository.impl.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscarRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(RestauranteRepositoryImpl.class);
        Restaurante restaurante = restauranteRepository.buscar(1);
        System.out.println(restaurante.getNome());
    }
}
