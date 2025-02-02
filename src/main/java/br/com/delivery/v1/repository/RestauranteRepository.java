package br.com.delivery.v1.repository;

import br.com.delivery.v1.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listar();

    Restaurante buscar(Integer id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Restaurante restaurante);
}
