package tijo.sportEventApp.sportEvent;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tijo.sportEventApp.sportEvent.domain.SportEventFacade;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto;
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.SportEventDto;

import java.util.List;

@RestController
@RequestMapping("/api/sportEvent")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SportEventController {
  SportEventFacade sportEventFacade;

  @PostMapping("/address/create")
  ResponseEntity<SportEventAddressDto> createSportEventAddress(@RequestBody CreateSportEventAddressDto eventAddress) {
    return ResponseEntity.ok(sportEventFacade.createEventAddress(eventAddress));
  }

  @PostMapping("/create")
  ResponseEntity<SportEventDto> createSportEvent(@RequestBody CreateSportEventDto sportEvent) {
    return ResponseEntity.ok(sportEventFacade.createSportEvent(sportEvent));
  }

  @GetMapping("/all")
  ResponseEntity<List<SportEventDto>> getAllSportEvents() {
    return ResponseEntity.ok(sportEventFacade.findAllSportEvents());
  }

  @GetMapping("/address/all")
  ResponseEntity<List<SportEventAddressDto>> getAllSportEventsAddresses() {
    return ResponseEntity.ok(sportEventFacade.findAllEventAddresses());
  }

  @GetMapping("/details/sportEventId")
  ResponseEntity<SportEventDto> getSportEvent(@PathVariable Long sportEventId) {
    return ResponseEntity.ok(sportEventFacade.findSportEventById(sportEventId));
  }

  @GetMapping("/address/details/sportEventAddressId")
  ResponseEntity<SportEventAddressDto> getSportEventAddress(@PathVariable Long sportEventAddressId) {
    return ResponseEntity.ok(sportEventFacade.findSportEventAddressById(sportEventAddressId));
  }

  @GetMapping("/eventType")
  ResponseEntity<List<SportEventDto>> getEventSportByType(@PathVariable String eventType) {
    return ResponseEntity.ok(sportEventFacade.findAllSportEventsByType(eventType));
  }

  @GetMapping(value = "/cleanup")
  @Hidden
  ResponseEntity<String> cleanup() {
    sportEventFacade.cleanup();
    return ResponseEntity.ok("Sport event cleanup");
  }
}
