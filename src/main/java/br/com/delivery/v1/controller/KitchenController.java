package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.service.KitchenService;
import io.reactivex.rxjava3.core.Single;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/kitchens")
@RequiredArgsConstructor
@Slf4j
public class KitchenController {

    private final KitchenService kitchenService;
    private final Validator validator;

    @GetMapping
    public ResponseEntity<GenericMessage> findAll() {
        return kitchenService.findAll()
                .flatMap(kitchens ->
                        Single.just(
                                ResponseEntity
                                        .ok(GenericMessage
                                                .builder()
                                                .success(true)
                                                .result(kitchens)
                                                .build()
                                        )
                        )
                )
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        return kitchenService
                .findById(id)
                .map(kitchen -> ResponseEntity
                        .ok(GenericMessage
                                .builder()
                                .success(true)
                                .result(kitchen)
                                .build()
                        )

                )
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PostMapping
    public ResponseEntity<GenericMessage> create(@RequestBody Kitchen kitchen) {
        return kitchenService.save(kitchen)
                .map(k ->
                        ResponseEntity
                                .ok()
                                .body(GenericMessage
                                        .builder()
                                        .success(true)
                                        .result(k)
                                        .build())
                )
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        return kitchenService.update(kitchen, id)
                .map(k -> ResponseEntity
                        .ok(GenericMessage
                                .builder()
                                .success(true)
                                .result(k)
                                .build()
                        )
                )
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        return kitchenService.deleteKitchen(id)
                .flatMap(g -> Single.just(ResponseEntity.status(HttpStatus.NO_CONTENT).body(g)))
                .onErrorReturn(e -> ResponseEntityUtils.genericMessageResponseEntity(e, Kitchen.class, id))
                .blockingGet();
    }
}