package br.com.delivery.v1.controller;

import br.com.delivery.v1.domain.entity.State;
import br.com.delivery.v1.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<State> findAll() {
        return stateService.findAll();
    }
}