package br.com.delivery.v1.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensagemRetorno {
    private boolean sucesso;
    private String mensagem;
    private Object resultado;
}
