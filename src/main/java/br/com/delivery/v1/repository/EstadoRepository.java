package br.com.delivery.v1.repository;

import br.com.delivery.v1.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(Integer id);
    Estado salvar(Estado estado);
    void remover(Estado estado);

}
