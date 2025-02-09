package br.com.delivery.v1.service;

import br.com.delivery.v1.exception.EntidadeEmUsoException;
import br.com.delivery.v1.exception.NaoEncontradoException;
import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.repository.CozinhaRepository;
import br.com.delivery.v1.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    public Cozinha buscar(Integer id) {
        return cozinhaRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(Utils.format("Cozinha de id {} não encontrada.", id)));
    }

    public Cozinha criar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    public Cozinha atualizar(Integer id, Cozinha cozinha) {
        Cozinha cozinhaAtual = buscar(id);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.save(cozinhaAtual);
    }

    public void remover(Integer id) {
        Cozinha cozinhaAtual = buscar(id);
        try {
            cozinhaRepository.delete(cozinhaAtual);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(Utils.format("Não é possível deletar cozinha de id {} pos ela está em uso."));
        }

    }
}
