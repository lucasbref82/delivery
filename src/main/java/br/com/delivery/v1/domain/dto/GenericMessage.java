package br.com.delivery.v1.domain.dto;

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
    private Object message;
    private Object result;
}
