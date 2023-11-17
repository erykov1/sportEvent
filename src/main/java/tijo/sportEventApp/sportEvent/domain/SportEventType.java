package tijo.sportEventApp.sportEvent.domain;

import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto;

enum SportEventType {
  VOLLEYBALL,
  HANDBALL,
  FOOTBALL,
  TENNIS,
  MARATHON,
  RUNNING;

  SportEventTypeDto dto() {
    return SportEventTypeDto.valueOf(name());
  }
}
