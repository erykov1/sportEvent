package tijo.sportEventApp.sportEvent.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SportEventConfiguration {
  @Bean
  SportEventFacade sportEventFacade(SportEventRepository sportEventRepository) {
    return new SportEventFacade(sportEventRepository);
  }
}
