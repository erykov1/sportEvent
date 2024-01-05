package tijo.sportEventApp.report.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateReportDto {
  String username;
  Long sportEventId;

  @JsonCreator
  public CreateReportDto(@JsonProperty("username") String username, @JsonProperty("sportEventId") Long sportEventId) {
    this.username = username;
    this.sportEventId = sportEventId;
  }
}

