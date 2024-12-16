package br.com.delivery.v1.controller;

import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/kitchens")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping()
    public List<Kitchen> findAll() {
        return kitchenService.findAll();
    }

    @GetMapping("/{id}")
    public Kitchen findById(@PathVariable Long id) {
        return kitchenService.findById(id);
    }
}
