package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.model.dto.MensagemRetorno;
import br.com.delivery.v1.service.RestauranteService;
import br.com.delivery.v1.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @PostMapping
    public ResponseEntity<MensagemRetorno> criar(@RequestBody Restaurante restaurante) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(restauranteService.criar(restaurante))
                        .build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemRetorno> atualizar(@PathVariable Integer id, @RequestBody Restaurante restaurante) {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(restauranteService.atualizar(id, restaurante))
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletar(@PathVariable Integer id) {
        restauranteService.remover(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(Utils.format("Restaurante de id {} deletado com sucesso", id))
                        .build()
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MensagemRetorno> atualizarParcialmente(@PathVariable Integer id, @RequestBody Map<String, Object> restaurante) {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .mensagem(Utils.format("Restaunte de id {} alterado com sucesso.", id))
                        .resultado(restauranteService.atualizarParcialmente(id, restaurante))
                        .build()
                );

    }
}
