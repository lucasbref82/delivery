package br.com.delivery.v1.notificacao;

import br.com.delivery.v1.model.Cliente;

public interface Notificador {

    void notificar(String mensagem, Cliente cliente);

}
