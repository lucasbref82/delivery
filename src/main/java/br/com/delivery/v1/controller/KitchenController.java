package br.com.delivery.v1.controller;

import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/kitchens")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping()
    public ResponseEntity<GenericMessage> findAll() {
        try {
            return ResponseEntity.ok(
                    GenericMessage
                            .builder()
                            .sucess(true)
                            .result(kitchenService.findAll())
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            GenericMessage
                                    .builder()
                                    .sucess(false)
                                    .message(e.getMessage())
                                    .build()
                    );
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(
                    GenericMessage
                            .builder()
                            .sucess(true)
                            .result(kitchenService.findById(id))
                            .build());
        } catch (RuntimeException e) {
            if (e.getCause() instanceof NotFoundException) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(GenericMessage
                                .builder()
                                .sucess(false)
                                .message(e.getMessage())
                                .build());
            }
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GenericMessage
                            .builder()
                            .sucess(false)
                            .message(e.getMessage())
                            .build());
        }
    }
}
