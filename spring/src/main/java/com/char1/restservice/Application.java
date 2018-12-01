package com.char1.restservice;

import com.char1.restservice.entity.User;
import com.char1.restservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support;

@SpringBootApplication // SpringBootServletInitializer is required for Tomcat deploy
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    protected CommandLineRunner init(final UserRepository userRepository) {
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setName("Administrator");
            user.setEmail("admin@javahelps.com");
            userRepository.save(user);
        };
    }
}
