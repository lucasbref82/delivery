package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/kitchens")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<GenericMessage> findAll() {
        try {
            return ResponseEntity
                    .ok(GenericMessage
                            .builder()
                            .success(true)
                            .result(kitchenService
                                    .findAll())
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.internalServerError(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(GenericMessage
                            .builder()
                            .success(true)
                            .result(kitchenService
                                    .findById(id))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @PostMapping
    public ResponseEntity<GenericMessage> create(@RequestBody Kitchen kitchen) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(GenericMessage
                            .builder()
                            .success(true)
                            .message("Successfully created kitchen.")
                            .result(kitchenService.save(kitchen))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.internalServerError(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        try {
            Kitchen currentKitchen = kitchenService.findById(id).blockingGet();
            BeanUtils.copyProperties(kitchen, currentKitchen, "id");
            return ResponseEntity
                    .ok(GenericMessage
                            .builder()
                            .success(true)
                            .message("Successfully updated kitchen.")
                            .result(kitchenService.save(kitchen))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        try {
            kitchenService.deleteKitchen(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntityUtils.conflictNotFoundOrInternalServerError(e, Kitchen.class, id);
        }
    }
}