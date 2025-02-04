package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.model.dto.MensagemRetorno;
import br.com.delivery.v1.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService restauranteService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MensagemRetorno> listar() {

        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(restauranteService.listar()).build()
                );
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MensagemRetorno> buscar(@PathVariable Integer id) {

        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(restauranteService.buscar(id)).build()
                );
    }
}
