package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Cozinha;
import br.com.delivery.v1.service.CozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaService.listar();
    }

}
