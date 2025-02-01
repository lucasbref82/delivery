package br.com.delivery.v1.main.cozinha;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.repository.impl.CozinhaRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ListarCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepositoryImpl cozinhaRepositoryImpl = applicationContext.getBean(CozinhaRepositoryImpl.class);
        cozinhaRepositoryImpl.listar().forEach(cozinha -> System.out.println(cozinha.getNome()));
    }


}
