package br.com.delivery.v1.service;

import br.com.delivery.v1.exception.NaoEncontradoException;
import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.repository.impl.CozinhaRepositoryImpl;
import br.com.delivery.v1.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CozinhaService {

    private final CozinhaRepositoryImpl cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    public Cozinha buscar(Integer id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);
        if (cozinha == null) {
            throw new NaoEncontradoException(Utils.format("Cozinha de id {} não encontrada.", id));
        }
        return cozinhaRepository.buscar(id);
    }

    public Cozinha criar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public Cozinha atualizar(Integer id, Cozinha cozinha) {
        Cozinha cozinhaAtual = buscar(id);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.salvar(cozinhaAtual);
    }

    public void remover(Integer id) {
        Cozinha cozinhaAtual = buscar(id);
        cozinhaRepository.remover(cozinhaAtual);
    }
}
