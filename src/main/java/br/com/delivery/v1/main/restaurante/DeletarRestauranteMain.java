package br.com.delivery.v1.main.restaurante;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.model.Restaurante;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletarRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(RestauranteRepositoryImpl.class);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1);
        restauranteRepository.remover(restaurante);
    }
}
