package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Restaurant;
import br.com.delivery.v1.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<GenericMessage> findAll() {
        try {
            return ResponseEntity
                    .ok(GenericMessage
                            .builder()
                            .sucess(true)
                            .result(restaurantService.findAll())
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
                            .sucess(true)
                            .result(restaurantService.findById(id))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @PostMapping()
    public ResponseEntity<GenericMessage> save(@RequestBody Restaurant restaurant) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(GenericMessage
                            .builder()
                            .sucess(true)
                            .message("Succesfully save resturant.")
                            .result(restaurantService
                                    .save(restaurant))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.internalServerError(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        try {
            Restaurant currentRestaurante = restaurantService.findById(id);
            BeanUtils.copyProperties(currentRestaurante, restaurant);
            return ResponseEntity
                    .ok(GenericMessage.builder()
                            .sucess(true)
                            .message("Restaurant successfully changed.")
                            .result(restaurantService
                                    .save(restaurant))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        try {
            restaurantService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(GenericMessage
                            .builder()
                            .sucess(true)
                            .message("Restaurant successfully deleted.")
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.conflictNotFoundOrInternalServerError(e, Restaurant.class, id);
        }
    }
}
