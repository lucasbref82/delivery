package br.com.delivery.domain.services;

import br.com.delivery.DeliveryApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class CozinhaMain {

    public static void main(String[] args) {

        /**
         * Cria configura e incia  a aplicação, definindo que não é web
         * aproveitando os beans escaneados da classe que tem a notação @SpringBootApplication
         */

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaService cozinhaService = applicationContext.getBean(CozinhaService.class);

        cozinhaService.listarCozinhas().forEach(c -> System.out.println(c.getNome()));

    }
}
