package br.com.delivery.v1.service;

import br.com.delivery.v1.exception.NaoEncontradoException;
import br.com.delivery.v1.model.Estado;
import br.com.delivery.v1.repository.EstadoRepository;
import br.com.delivery.v1.repository.impl.EstadoRepositoryImpl;
import br.com.delivery.v1.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepositoryImpl estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado buscar(Integer id) {
        Estado estado = estadoRepository.buscar(id);
        if (estado == null) {
            throw new NaoEncontradoException("Estado de id " + id + " não encontrado !");
        }
        return estado;
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public Estado atualizar(Estado estado, Integer id) {
        Estado estadoAtual = estadoRepository.buscar(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.salvar(estadoAtual);
    }

    public void deletar(Integer id) {
        Estado estadoAtual = estadoRepository.buscar(id);
        estadoRepository.remover(estadoAtual);
    }

    public Estado atualizarParcialmente(Integer id, Map<String, Object> campos) {
        Estado estadoAtual = buscar(id);
        Utils.merge(campos, estadoAtual, Estado.class);
        return atualizar(estadoAtual, id);
    }
}
