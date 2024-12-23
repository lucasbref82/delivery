package br.com.delivery.v1.controller;

import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.City;
import br.com.delivery.v1.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<GenericMessage> findAll() {
        try {
            return ResponseEntity
                    .ok(GenericMessage
                            .builder()
                            .success(true)
                            .result(cityService.findAll().blockingGet())
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.internalServerError(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        try {
            return
                    ResponseEntity
                            .ok(GenericMessage
                                    .builder()
                                    .success(true)
                                    .result(cityService.findById(id).blockingGet())
                                    .build()
                            );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @PostMapping
    public ResponseEntity<GenericMessage> create(@RequestBody City city) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(GenericMessage
                            .builder()
                            .success(true)
                            .result(
                                    cityService
                                            .save(city)
                                            .blockingGet()
                            )
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
     }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@PathVariable Long id, @RequestBody City city) {
        try {
            City currentCity = cityService.findById(id).blockingGet();
            BeanUtils.copyProperties(city, currentCity, "id");
            return ResponseEntity
                    .ok(GenericMessage.builder()
                            .success(true)
                            .message("City successfully changed.")
                            .result(cityService
                                    .save(currentCity))
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.notFoundOrInternalServerError(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        try {
            cityService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(GenericMessage
                            .builder()
                            .success(true)
                            .message("Restaurant successfully deleted.")
                            .build()
                    );
        } catch (Exception e) {
            return ResponseEntityUtils.conflictNotFoundOrInternalServerError(e, City.class, id);
        }
    }
}
