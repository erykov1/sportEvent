package tijo.sportEventApp.sportEvent.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import tijo.sportEventApp.report.dto.SportEventAssignDto;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SportEventPublisher {
  ApplicationEventPublisher applicationEventPublisher;

  void notifySportEventCreated(SportEventAssignDto sportEventPublish) {
    applicationEventPublisher.publishEvent(sportEventPublish);
  }
}
