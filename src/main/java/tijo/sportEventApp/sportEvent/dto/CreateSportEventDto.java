package tijo.sportEventApp.sportEvent.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateSportEventDto {
  String eventName;
  Instant eventTime;
  Instant registrationDeadline;
  String description;
  Long maxParticipants;
  @Enumerated(EnumType.STRING)
  SportEventTypeDto sportEventType;
  Long eventAddress;
}
