package br.com.delivery.v1.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class Utils {

    private Utils() {
    }

    public static String format(String format, Object... arguments) {
        return MessageFormatter.basicArrayFormat(format, arguments);
    }
    /**
     * Atualiza os atributos de um objeto (objetoDestino) com base nos valores informados em um mapa.
     *
     * @param camposParaAtualizar mapa contendo os nomes dos atributos e os valores a serem atualizados
     * @param objetoDestino         objeto que sofrerá as atualizações
     * @param classeDestino         classe do objeto que será atualizado
     * @param <T>                   tipo do objeto
     * @throws IllegalArgumentException se os parâmetros forem nulos
     */
    public static <T> void merge(Map<String, Object> camposParaAtualizar, T objetoDestino, Class<T> classeDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Converte o mapa para uma instância temporária do tipo da classe de destino
        T objetoOrigem = objectMapper.convertValue(camposParaAtualizar, classeDestino);

        // Para cada entrada do mapa, localiza o campo correspondente e atualiza o valor, se não for nulo
        camposParaAtualizar.forEach((nomeCampo, valorCampo) -> {
            Field campo = ReflectionUtils.findField(classeDestino, nomeCampo);
            if (campo != null) {
                campo.setAccessible(true);
                // Obtém o novo valor do objeto de origem
                Object novoValor = ReflectionUtils.getField(campo, objetoOrigem);

                if (novoValor != null) {
                    ReflectionUtils.setField(campo, objetoDestino, novoValor);
                }
            }
        });
    }
}
