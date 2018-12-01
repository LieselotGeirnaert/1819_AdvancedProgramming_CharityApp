package com.char1.api;

import com.char1.api.entity.User;
import com.char1.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
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
