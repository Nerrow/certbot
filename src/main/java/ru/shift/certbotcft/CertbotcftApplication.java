package ru.shift.certbotcft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class CertbotcftApplication {

    public static void main(String[] args) {
        SpringApplication.run(CertbotcftApplication.class, args);
    }

}
