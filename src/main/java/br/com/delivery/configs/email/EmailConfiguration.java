package br.com.delivery.configs.email;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfiguration {

    private final EmailData emailData;

    @Bean
    public Session mailSession() {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imaps.host", emailData.getEmailHost());
        properties.setProperty("mail.imaps.port", emailData.getEmailPort());
        properties.setProperty("mail.imaps.ssl.enable", "true");  // Habilitar SSL para Gmail
        properties.setProperty("mail.imaps.auth", "true");         // Habilitar autenticação
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        return session;
    }

    @Bean
    public Store mailStore() throws NoSuchProviderException {
        Store store = mailSession().getStore("imaps");
        try {
            store.connect(emailData.getEmailHost(), emailData.getEmailUsername(), emailData.getEmailPassword());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return store;
    }

    @Bean
    public Folder mailFolder() throws MessagingException {
        // Conectar à pasta INBOX do Gmail
        Folder folder = mailStore().getFolder("INBOX");
        folder.open(Folder.READ_ONLY);  // Abrir a pasta para leitura
        return folder;
    }

}

