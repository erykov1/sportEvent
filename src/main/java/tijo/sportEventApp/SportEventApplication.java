package tijo.sportEventApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import tijo.sportEventApp.security.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class SportEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportEventApplication.class, args);
	}

}
