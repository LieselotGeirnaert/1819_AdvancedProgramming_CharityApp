package com.char1.api;

import com.char1.api.entity.User;
import com.char1.api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // SpringBootServletInitializer needed for .WAR
public class RestServerApplication extends SpringBootServletInitializer {

	@Override // Method needed for .WAR
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

	@Bean
	protected CommandLineRunner init(final UserRepository userRepository) {
		return args -> {
			User user = new User();
			user.setPassword("admin");
			user.setLastName("Administrator");
			user.setEmailAddress("admin@char1.com");
			userRepository.save(user);
		};
	}

}
