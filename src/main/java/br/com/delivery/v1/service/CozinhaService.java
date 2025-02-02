package br.com.delivery.v1.service;

import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.repository.impl.CozinhaRepositoryImpl;
import lombok.RequiredArgsConstructor;
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
        return cozinhaRepository.buscar(id);
    }
}
