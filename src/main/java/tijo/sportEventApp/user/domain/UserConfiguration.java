package tijo.sportEventApp.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UserConfiguration {
  @Bean
  UserFacade userFacade(UserRepository userRepository) {
    return new UserFacade(userRepository);
  }
}
