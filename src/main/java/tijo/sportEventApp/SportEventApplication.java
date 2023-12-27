package tijo.sportEventApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import tijo.sportEventApp.security.RsaKeyProperties;
import tijo.sportEventApp.user.domain.UserFacade;
import tijo.sportEventApp.user.dto.CreateUserDto;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SportEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportEventApplication.class, args);
	}
}
