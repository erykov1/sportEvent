package tijo.sportEventApp.sportEvent.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.utils.InstantDeserializer;
import tijo.sportEventApp.utils.InstantSerializer;

import java.time.Instant;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateSportEventDto {
  String eventName;
  @JsonSerialize(using = InstantSerializer.class)
  @JsonDeserialize(using = InstantDeserializer.class)
  Instant eventTime;
  @JsonSerialize(using = InstantSerializer.class)
  @JsonDeserialize(using = InstantDeserializer.class)
  Instant registrationDeadline;
  String description;
  Long maxParticipants;
  @Enumerated(EnumType.STRING)
  SportEventTypeDto sportEventType;
  Long sportEventAddress;

  @JsonCreator
  public CreateSportEventDto(@JsonProperty("eventName") String eventName, @JsonProperty("eventTime") Instant eventTime,
                             @JsonProperty("registrationDeadline") Instant registrationDeadline, @JsonProperty("description") String description,
                             @JsonProperty("maxParticipants") Long maxParticipants, @JsonProperty("sportEventType") SportEventTypeDto sportEventType,
                             @JsonProperty("sportEventAddress") Long sportEventAddress) {
    this.eventName = eventName;
    this.eventTime = eventTime;
    this.registrationDeadline = registrationDeadline;
    this.description = description;
    this.maxParticipants = maxParticipants;
    this.sportEventType = sportEventType;
    this.sportEventAddress = sportEventAddress;
  }
}

