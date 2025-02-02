package br.com.delivery.v1.main.restaurante;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.repository.impl.RestauranteRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class AtualizarRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepositoryImpl restauranteRepository = applicationContext.getBean(RestauranteRepositoryImpl.class);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1);
        restaurante.setTaxaFrete(new BigDecimal("17.50"));
        restaurante.setNome("Thai Delivery (Sob nova direção)");
        restauranteRepository.salvar(restaurante);
    }
}
