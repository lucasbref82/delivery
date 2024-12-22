package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.State;
import br.com.delivery.v1.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping()
    public ResponseEntity<GenericMessage> findAll() {
        try {
            return ResponseEntity.ok(GenericMessage.builder().success(true).result(stateService.findAll()).build());
        } catch (Exception e) {
            return ResponseEntityUtils.internalServerError(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(GenericMessage
                    .builder()
                    .success(true)
                    .result(stateService.findById(id).blockingGet())
                    .build()
            );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @PostMapping()
    public ResponseEntity<GenericMessage> save(@RequestBody State state) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(GenericMessage.builder().success(true).result(stateService.save(state)).build())
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@RequestBody State state, @PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(GenericMessage
                            .builder()
                            .success(true)
                            .result(stateService
                                    .update(id, state)
                            )
                            .build()
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
