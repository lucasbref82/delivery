package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Estado;
import br.com.delivery.v1.model.wrapper.EstadosWrapper;
import br.com.delivery.v1.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public EstadosWrapper listarXml() {
        return new EstadosWrapper(estadoService.listar());
    }

    @GetMapping("/{id}")
    public Estado buscar(@PathVariable Integer id) {
        return estadoService.buscar(id);
    }
}
