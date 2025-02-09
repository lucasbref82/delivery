package br.com.delivery.v1.service;

import br.com.delivery.v1.exception.NaoEncontradoException;
import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.repository.RestauranteRepository;
import br.com.delivery.v1.repository.impl.RestauranteRepositoryImpl;
import br.com.delivery.v1.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Integer id) {
        return restauranteRepository
                .findById(id)
                .orElseThrow(() -> new NaoEncontradoException(Utils.format("Restaurante de id {} não encontrado")));
    }

    public Restaurante criar(Restaurante restaurante) {
        if (restaurante.getCozinha() != null) {
            Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
        }
        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Integer id, Restaurante restaurante) {
        Restaurante restauranteAtual = buscar(id);
        if (restaurante.getCozinha() != null) {
            Cozinha cozinha = cozinhaService.buscar(id);
            restaurante.setCozinha(cozinha);
        }
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        return restauranteRepository.save(restauranteAtual);
    }

    public void remover(Integer id) {
        Restaurante restauranteAtual = buscar(id);
        restauranteRepository.delete(restauranteAtual);
    }

    public Restaurante atualizarParcialmente(Integer id, Map<String, Object> campos) {
        Restaurante restauranteAtual = buscar(id);
        Utils.merge(campos, restauranteAtual, Restaurante.class);
        return atualizar(id, restauranteAtual);
    }
}
