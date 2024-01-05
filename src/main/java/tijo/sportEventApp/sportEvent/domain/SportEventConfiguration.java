package tijo.sportEventApp.sportEvent.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SportEventConfiguration {
  @Bean
  SportEventFacade sportEventFacade(SportEventRepository sportEventRepository,
                                    SportEventAddressRepository sportEventAddressRepository, ApplicationEventPublisher eventPublisher) {
    return SportEventFacade.builder()
        .sportEventRepository(sportEventRepository)
        .sportEventAddressRepository(sportEventAddressRepository)
        .sportEventPublisher(new SportEventPublisher(eventPublisher))
        .build();
  }

  SportEventFacade sportEventFacade(ApplicationEventPublisher eventPublisher) {
    return SportEventFacade.builder()
        .sportEventRepository(new InMemorySportEventRepository())
        .sportEventAddressRepository(new InMemorySportEventAddressRepository())
        .sportEventPublisher(new SportEventPublisher(eventPublisher))
        .build();
  }
}
