package br.com.delivery.v1.main.cozinha;

import br.com.delivery.DeliveryApplication;
import br.com.delivery.v1.main.cozinha.crud.CozinhaCrud;
import br.com.delivery.v1.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletarCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(DeliveryApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaCrud cozinhaCrud = applicationContext.getBean(CozinhaCrud.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1);
        cozinha.setNome("Tailandesa");

        cozinhaCrud.deletar(cozinha);
    }
}
