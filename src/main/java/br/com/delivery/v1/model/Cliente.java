package br.com.delivery.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private String nome;
    private String email;
    private String telefone;
    private boolean ativo = false;


    public void ativar() {
        this.ativo = true;
    }
}
