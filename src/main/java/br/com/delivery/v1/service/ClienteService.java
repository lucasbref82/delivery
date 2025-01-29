package br.com.delivery.v1.service;

import br.com.delivery.v1.model.Cliente;
import br.com.delivery.v1.notificacao.NivelUrgencia;
import br.com.delivery.v1.notificacao.Notificador;
import br.com.delivery.v1.notificacao.TipoDoNotificador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @TipoDoNotificador(NivelUrgencia.URGENTE)
    private final Notificador notificador;


    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar("Seu cadastro está ativo !", cliente);
    }
}
