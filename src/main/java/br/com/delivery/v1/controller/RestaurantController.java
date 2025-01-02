package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Restaurant;
import br.com.delivery.v1.domain.service.RestaurantService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
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
        return restaurantService.findAll()
                .map(restaurant -> ResponseEntity.ok(GenericMessage.builder().success(true).result(restaurant).build()))
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        return restaurantService.findById(id)
                .flatMap(r -> Maybe.just(ResponseEntity.ok(GenericMessage.builder().success(true).result(r).build())))
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PostMapping()
    public ResponseEntity<GenericMessage> save(@RequestBody Restaurant restaurant) {
        return restaurantService.save(restaurant)
                .flatMap(r ->
                        Single.just(
                                ResponseEntity
                                        .status(HttpStatus.CREATED)
                                        .body(
                                                GenericMessage
                                                        .builder()
                                                        .success(true)
                                                        .result(r)
                                                        .build())
                        )
                ).onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.findById(id)
                .flatMap(r -> {
                    BeanUtils.copyProperties(restaurant, r, "id", "address", "paymentMethods", "resgistrationDate");
                    return Single.just(ResponseEntity
                            .ok(GenericMessage
                                    .builder()
                                    .success(true)
                                    .result(restaurantService.save(r))
                                    .build()
                            )
                    );
                })
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        try {
            restaurantService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(GenericMessage
                            .builder()
                            .success(true)
                            .message("Restaurant successfully deleted.")
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.genericMessageResponseEntity(e, Restaurant.class, id);
        }
    }
}
