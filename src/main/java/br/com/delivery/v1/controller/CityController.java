package br.com.delivery.v1.controller;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.ResponseEntityUtils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.City;
import br.com.delivery.v1.domain.service.CityService;
import io.reactivex.rxjava3.core.Single;
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
    private final SchedulersConfig schedulersConfig;

    @GetMapping
    public ResponseEntity<GenericMessage> findAll() {
        return cityService.findAll()
                .subscribeOn(schedulersConfig.defaultScheduler())
                .map(s -> GenericMessage.builder().success(true).result(s).build())
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericMessage> findById(@PathVariable Long id) {
        return cityService.findById(id)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .map(s -> GenericMessage.builder().success(true).result(s).build())
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PostMapping
    public ResponseEntity<GenericMessage> create(@RequestBody City city) {
        return cityService.save(city)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .map(c -> GenericMessage.builder().success(true).result(c).build())
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericMessage> update(@PathVariable Long id, @RequestBody City city) {
        return cityService.findById(id)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .map(existingCity -> {
                    BeanUtils.copyProperties(city, existingCity, "id");
                    return cityService.save(existingCity)
                            .map(c -> GenericMessage
                                    .builder()
                                    .success(true)
                                    .result(c)
                                    .build()
                            )
                            .blockingGet();
                })
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntityUtils::genericMessageResponseEntity)
                .blockingGet();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericMessage> delete(@PathVariable Long id) {
        return cityService.delete(id)
                .flatMap(r -> Single.just(ResponseEntity.status(HttpStatus.NO_CONTENT).body(r)))
                .onErrorReturn(e -> ResponseEntityUtils.genericMessageResponseEntity(e, City.class, id))
                .blockingGet();
    }
}
