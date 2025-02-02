package br.com.delivery.v1.model.wrapper;

import br.com.delivery.v1.model.Estado;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
// Adicionando o nome estados ao root do XML
@JacksonXmlRootElement(localName = "estados")
public class EstadosWrapper {

    // Alterando o nome da propriedade para estados
    @JacksonXmlProperty(localName = "estado")

    // Desabilita o embrulho que o xml tem
    @JacksonXmlElementWrapper(useWrapping = false)
    @NonNull
    private List<Estado> estados;

}
