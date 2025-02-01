package br.com.delivery.v1.main.cozinha;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.repository.impl.CozinhaRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscarCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepositoryImpl cozinhaRepositoryImpl = applicationContext.getBean(CozinhaRepositoryImpl.class);
        Cozinha cozinha = cozinhaRepositoryImpl.buscar(1);
        System.out.println(cozinha.getNome());

    }
}
