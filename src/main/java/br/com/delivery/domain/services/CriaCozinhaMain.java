package br.com.delivery.domain.services;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.domain.entities.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class CriaCozinhaMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaService cozinhaService = applicationContext.getBean(CozinhaService.class);
        Cozinha brasileira = new Cozinha();
        brasileira.setNome("Brasileira");
        Cozinha japonesa  = new Cozinha();
        japonesa.setNome("Japonesa");
        cozinhaService.salvarTodasCozinhas(List.of(brasileira, japonesa));

    }
}
