package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Estado;
import br.com.delivery.v1.model.dto.MensagemRetorno;
import br.com.delivery.v1.model.wrapper.EstadosWrapper;
import br.com.delivery.v1.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<MensagemRetorno> listar() {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(estadoService.listar()).build()
                );
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<MensagemRetorno> listarXml() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(new EstadosWrapper(estadoService.listar()))
                        .build()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensagemRetorno> buscar(@PathVariable Integer id) {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .resultado(estadoService.buscar(id)).build()
                );
    }

    @PostMapping
    public ResponseEntity<MensagemRetorno> salvar(@RequestBody Estado estado) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .mensagem("Estado salvo com sucesso.")
                        .resultado(estadoService.salvar(estado))
                        .build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemRetorno> atualizar(@RequestBody Estado estado, @PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(MensagemRetorno
                        .builder()
                        .resultado(estadoService.atualizar(estado, id))
                        .build()
                );
    }
}
