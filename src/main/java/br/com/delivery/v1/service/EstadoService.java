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

    private final EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }


    public Estado buscar(Integer id) {
        return estadoRepository
                .findById(id)
                .orElseThrow(() -> new NaoEncontradoException(Utils.format("Estado de id {} não encontrado.")));
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Estado estado, Integer id) {
        Estado estadoAtual = buscar(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    public void deletar(Integer id) {
        Estado estadoAtual = buscar(id);
        estadoRepository.delete(estadoAtual);
    }

    public Estado atualizarParcialmente(Integer id, Map<String, Object> campos) {
        Estado estadoAtual = buscar(id);
        Utils.merge(campos, estadoAtual, Estado.class);
        return atualizar(estadoAtual, id);
    }
}
