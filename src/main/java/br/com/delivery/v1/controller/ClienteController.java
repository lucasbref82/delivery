package br.com.delivery.v1.controller;

import br.com.delivery.v1.model.Cliente;
import br.com.delivery.v1.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/clientes", name = "Classe responsável por cuidar dos clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public void ativaCliente() {
        Cliente cliente = new Cliente("Joao", "joao@xpto.com.br", "31994372780", false);
        clienteService.ativar(cliente);
    }

}
