package br.com.delivery.v1.controller;

import br.com.delivery.v1.exception.NaoEncontradoException;
import br.com.delivery.v1.model.Estado;
import br.com.delivery.v1.model.wrapper.EstadosWrapper;
import br.com.delivery.v1.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.listar());
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EstadosWrapper> listarXml() {
        return ResponseEntity.status(HttpStatus.OK).body(new EstadosWrapper(estadoService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(estadoService.buscar(id));
    }
}
