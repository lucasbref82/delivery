package br.com.delivery.v1.service;

import br.com.delivery.v1.exception.NaoEncontradoException;
import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.model.Restaurante;
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

    private final RestauranteRepositoryImpl restauranteRepository;
    private final CozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Integer id) {
        Restaurante restaurante = restauranteRepository.buscar(id);
        if (restaurante == null) {
            throw new NaoEncontradoException(Utils.format("Restaurante de id {} não encontrado.", id));
        }
        return restauranteRepository.buscar(id);
    }

    public Restaurante criar(Restaurante restaurante) {
        if (restaurante.getCozinha() != null) {
            Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());
            restaurante.setCozinha(cozinha);
        }
        return restauranteRepository.salvar(restaurante);
    }

    public Restaurante atualizar(Integer id, Restaurante restaurante) {
        Restaurante restauranteAtual = buscar(id);
        if (restaurante.getCozinha() != null) {
            Cozinha cozinha = cozinhaService.buscar(id);
            restaurante.setCozinha(cozinha);
        }
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        return restauranteRepository.salvar(restauranteAtual);
    }

    public void remover(Integer id) {
        Restaurante restauranteAtual = buscar(id);
        restauranteRepository.remover(restauranteAtual);
    }

    public Restaurante atualizarParcialmente(Integer id, Map<String, Object> campos) {
        Restaurante restauranteAtual = buscar(id);
        merge(campos, restauranteAtual);
        return atualizar(id, restauranteAtual);
    }

    private void merge(Map<String, Object> campos, Restaurante restauranteAtual) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(campos, Restaurante.class);
        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            if (field != null) {
                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
                field.setAccessible(true);
                ReflectionUtils.setField(field, restauranteAtual, novoValor);
            }
        });
    }
}
