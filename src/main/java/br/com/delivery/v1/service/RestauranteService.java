package br.com.delivery.v1.service;

import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.repository.impl.RestauranteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepositoryImpl restauranteRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }
}
