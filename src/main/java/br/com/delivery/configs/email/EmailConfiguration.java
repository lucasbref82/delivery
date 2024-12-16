package br.com.delivery.configs.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.*;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class EmailConfiguration {

    private final EmailData emailData;

    @Bean
    public Session mailSession() {
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imaps.host", emailData.getEmailHost());
        properties.setProperty("mail.imaps.port", emailData.getEmailPort());
        properties.setProperty("mail.imaps.ssl.enable", "true");
        properties.setProperty("mail.imaps.auth", "true");
        Session session = Session.getInstance(properties);
        session.setDebug(true);
        return session;
    }

    @Bean
    public Store mailStore() throws MessagingException {
        Store store = mailSession().getStore("imaps");
        try {
            store.connect(emailData.getEmailHost(), emailData.getEmailUsername(), emailData.getEmailPassword());
        } catch (MessagingException e) {
            log.error("Erro ao conectar usuário {}, com a senha {}, motivo {}.", emailData.getEmailUsername(), emailData.getEmailPassword(), e.getMessage());
        }
        return store;
    }

    @Bean
    public Folder mailFolder() throws MessagingException {
        Store store = mailStore();
        if (store.isConnected()) {
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            return folder;
        }
        throw new MessagingException("Não foi possível conectar a caixa de email.");

    }
}

