package tijo.sportEventApp.sportEvent.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventPublishDto {
  Long sportEventId;
  Long maxParticipants;
  Instant registrationDeadline;
  Instant eventTime;
}
