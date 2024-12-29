package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Restaurant;
import br.com.delivery.v1.domain.entity.State;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.service.StateService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @GetMapping
    public ResponseEntity<GenericMessage> findAll() {
        return stateService.findAll()
                .flatMap(s -> Maybe.just(ResponseEntity.ok(GenericMessage.builder().success(true).result(s).build())))
                .blockingGet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        return stateService.findById(id)
                .flatMap(s ->
                        Single.just(
                                ResponseEntity.ok(GenericMessage
                                        .builder()
                                        .success(true)
                                        .result(s)
                                        .build()
                                )
                        )
                )
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PostMapping
    public ResponseEntity<GenericMessage> save(@RequestBody State state) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(GenericMessage
                            .builder()
                            .success(true)
                            .result(stateService
                                    .save(state)
                                    .blockingGet()
                            )
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.genericMessageResponseEntity(e);
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
                                    .blockingGet()
                            )
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.genericMessageResponseEntity(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT
                    ).body(GenericMessage
                            .builder()
                            .success(true)
                            .message(Utils.format("State of id {} succesfully deleted.", id))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.genericMessageResponseEntity(e, Restaurant.class, id);
        }
    }

}
