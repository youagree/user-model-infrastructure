
package ru.unit_techno.user.model.impl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class SendPasswordByMailConfig {
    @Value("${user-model.mail.host}")
    private String host;

    @Value("${user-model.mail.username}")
    private String username;

    @Value("${user-model.mail.password}")
    private String password;

    @Value("${user-model.mail.port}")
    private int port;

    @Value("${user-model.mail.protocol}")
    private String protocol;

    @Value("${user-model.debug}")
    private String debug;

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties properties = mailSender.getJavaMailProperties();

        properties.setProperty("mail.transport.protocol", protocol);
        properties.setProperty("mail.debug", debug);

        return mailSender;
    }
}