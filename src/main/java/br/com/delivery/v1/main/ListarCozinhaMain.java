package br.com.delivery.v1.main;

import br.com.delivery.DeliveryApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ListarCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaCrud cozinhaCrud = applicationContext.getBean(CozinhaCrud.class);
        cozinhaCrud.listar().forEach(cozinha -> System.out.println(cozinha.getNome()));
    }


}
