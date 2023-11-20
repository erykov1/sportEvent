package tijo.sportEventApp.sportEvent.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tijo.sportEventApp.utils.InstantProvider;

@Configuration
class SportEventConfiguration {
  @Bean
  SportEventFacade sportEventFacade(SportEventRepository sportEventRepository,
                                    SportEventAddressRepository sportEventAddressRepository) {
    return SportEventFacade.builder()
            .sportEventRepository(sportEventRepository)
            .sportEventAddressRepository(sportEventAddressRepository)
            .instantProvider(new InstantProvider())
            .build();
  }

  SportEventFacade sportEventFacade() {
    return SportEventFacade.builder()
            .sportEventRepository(new InMemorySportEventRepository())
            .sportEventAddressRepository(new InMemorySportEventAddressRepository())
            .instantProvider(new InstantProvider())
            .build();
  }
}
