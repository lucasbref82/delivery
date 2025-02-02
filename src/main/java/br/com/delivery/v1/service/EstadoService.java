package br.com.delivery.v1.service;

import br.com.delivery.v1.model.Estado;
import br.com.delivery.v1.repository.impl.EstadoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepositoryImpl estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }
}
