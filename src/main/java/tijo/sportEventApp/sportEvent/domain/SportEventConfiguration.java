package tijo.sportEventApp.sportEvent.domain;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tijo.sportEventApp.utils.InstantProvider;

@Configuration
class SportEventConfiguration {
  @Bean
  SportEventFacade sportEventFacade(SportEventRepository sportEventRepository,
                                    SportEventAddressRepository sportEventAddressRepository, ApplicationEventPublisher eventPublisher) {
    return SportEventFacade.builder()
        .sportEventRepository(sportEventRepository)
        .sportEventAddressRepository(sportEventAddressRepository)
        .instantProvider(new InstantProvider())
        .sportEventPublisher(new SportEventPublisher(eventPublisher))
        .build();
  }

  SportEventFacade sportEventFacade(ApplicationEventPublisher eventPublisher) {
    return SportEventFacade.builder()
        .sportEventRepository(new InMemorySportEventRepository())
        .sportEventAddressRepository(new InMemorySportEventAddressRepository())
        .instantProvider(new InstantProvider())
        .sportEventPublisher(new SportEventPublisher(eventPublisher))
        .build();
  }
}
