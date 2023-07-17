package lend;

import static lend.security.Role.ADMIN;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lend.service.AuthenticationService;
import lend.security.RegisterRequest;

@SpringBootApplication
public class BookLendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookLendingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.name("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.registerAdmin(admin).getAccessToken());
		};
	}
}
