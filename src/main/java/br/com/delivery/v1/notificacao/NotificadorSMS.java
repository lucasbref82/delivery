package br.com.delivery.v1.notificacao;

import br.com.delivery.v1.model.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(String mensagem, Cliente cliente) {
        System.out.printf("Notificando cliente %s através do telefone %s: %s", cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
