package br.com.delivery.v1.repository;

import br.com.delivery.v1.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listar();

    Cozinha buscar(Integer id);

    Cozinha salvar(Cozinha cozinha);

    void remover(Cozinha cozinha);
}
