package tijo.sportEventApp.sportEvent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.sportEvent.dto.SportEventDto;

import java.time.Instant;

@Entity
@Table(name = "sport_events")
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long sportEventId;
  String eventName;
  Instant eventTime;
  Instant registrationDeadline;
  String description;
  Long maxParticipants;
  @Enumerated(EnumType.STRING)
  SportEventType sportEventType;
  Long sportEventAddress;
}

  SportEventDto dto() {
    return SportEventDto.builder()
            .sportEventId(sportEventId)
            .eventName(eventName)
            .eventTime(eventTime)
            .registrationDeadline(registrationDeadline)
            .description(description)
            .maxParticipants(maxParticipants)
            .sportEventType(sportEventType.dto())
            .sportEventAddress(sportEventAddress)
            .build();
  }
}
