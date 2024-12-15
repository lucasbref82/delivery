package br.com.delivery.domain.services;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.domain.entities.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExclusaoCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        var cozinhaService = applicationContext.getBean(CozinhaService.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cozinha.setNome("Tailandesa");

        cozinhaService.exclusaoCozinha(cozinha);

    }
}
