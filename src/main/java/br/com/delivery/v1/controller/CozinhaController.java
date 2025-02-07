package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.model.dto.MensagemRetorno;
import br.com.delivery.v1.service.CozinhaService;
import br.com.delivery.v1.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CozinhaService cozinhaService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MensagemRetorno> listar() {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(cozinhaService.listar())
                        .build())
                ;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<MensagemRetorno> buscar(@PathVariable Integer id) {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(cozinhaService.buscar(id)).build()
                );
    }

    @PostMapping
    public ResponseEntity<MensagemRetorno> criar(@RequestBody Cozinha cozinha) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MensagemRetorno.builder()
                        .sucesso(true)
                        .mensagem("Cozinha criada com sucesso.")
                        .resultado(cozinhaService.criar(cozinha))
                        .build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemRetorno> atualizar(@PathVariable Integer id, @RequestBody Cozinha cozinha) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .mensagem(Utils.format("Cozinha de id {} atualizada com sucesso.", id))
                        .resultado(cozinhaService.atualizar(id, cozinha))
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> remover(@PathVariable Integer id) {
        cozinhaService.remover(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .mensagem(Utils.format("Cozinha de id {} deletada com sucesso."))
                        .build()
                );
    }
}
