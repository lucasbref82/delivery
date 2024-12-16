package br.com.delivery.configs.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("email")
@Data
public class EmailData {
    private String emailHost;
    private String emailPort;
    private String emailUsername;
    private String emailPassword;
}
