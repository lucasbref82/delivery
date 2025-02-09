package br.com.delivery.v1.notificacao;

import br.com.delivery.v1.model.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(String mensagem, Cliente cliente) {
        System.out.printf("Notificando cliente %s através do e-mail %s: %s", cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
