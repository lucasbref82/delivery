package br.com.delivery.v1.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericMessage {
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object result;
}
