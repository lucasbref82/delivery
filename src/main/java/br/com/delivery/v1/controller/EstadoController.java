package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Estado;
import br.com.delivery.v1.model.dto.MensagemRetorno;
import br.com.delivery.v1.model.wrapper.EstadosWrapper;
import br.com.delivery.v1.service.EstadoService;
import br.com.delivery.v1.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
                        .sucesso(true)
                        .resultado(estadoService.atualizar(estado, id))
                        .build()
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemRetorno> deletar(@PathVariable Integer id) {
        estadoService.deletar(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .mensagem(Utils.format("Estado de id {} deletado com sucesso", id))
                        .build()
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MensagemRetorno> atualizarParcialmente(@PathVariable Integer id, Map<String, Object> campos) {
        return ResponseEntity
                .ok(MensagemRetorno
                        .builder()
                        .sucesso(true)
                        .mensagem(Utils.format("Estados de id {} atualizado parcialemten."))
                        .resultado(estadoService.atualizarParcialmente(id, campos))
                        .build()
                );
    }
}
