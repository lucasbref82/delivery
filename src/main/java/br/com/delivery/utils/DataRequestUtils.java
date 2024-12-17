package br.com.delivery.utils;

import br.com.delivery.v1.domain.entity.DataRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataRequestUtils {

    private final HttpServletRequest request;

    @SneakyThrows
    public DataRequest buildDataRequest(Object obj, boolean salvar) {
        ObjectMapper mapper = JacksonUtils.createMapper();

        DataRequest apiRequest = DataRequest.builder()
                .dateRequest(LocalDateTime.now())
                .uri(Strings.isEmpty(request.getQueryString())
                        ? request.getRequestURI()
                        : Utils.format("{}?{}", request.getRequestURI(), request.getQueryString()))
                .method(request.getMethod())
                .requestBody(obj == null ? null : mapper.writeValueAsString(obj))
                .headers(mapper.writeValueAsString(Collections.list(request.getHeaderNames())
                        .stream()
                        .collect(Collectors.toMap(
                                Function.identity(),
                                h -> Collections.list(request.getHeaders(h)),
                                (oldValue, newValue) -> newValue,
                                HttpHeaders::new
                        ))))
                .build();
        if (salvar) {
            apiRequestRepository.save(apiRequest);
        }
        return apiRequest;
    }
}
