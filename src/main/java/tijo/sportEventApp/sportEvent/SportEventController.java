package tijo.sportEventApp.sportEvent;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tijo.sportEventApp.sportEvent.domain.SportEventFacade;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto;
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.SportEventDto;

import java.util.List;

@RestController
@RequestMapping("/api/sportEvent")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEventController {
  SportEventFacade sportEventFacade;

  @Autowired
  SportEventController(SportEventFacade sportEventFacade) {
    this.sportEventFacade = sportEventFacade;
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/address/create")
  ResponseEntity<SportEventAddressDto> createSportEventAddress(@RequestBody CreateSportEventAddressDto eventAddress) {
    return ResponseEntity.ok(sportEventFacade.createEventAddress(eventAddress));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create")
  ResponseEntity<SportEventDto> createSportEvent(@RequestBody CreateSportEventDto sportEvent) {
    return ResponseEntity.ok(sportEventFacade.createSportEvent(sportEvent));
  }

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping("/all")
  ResponseEntity<List<SportEventDto>> getAllSportEvents() {
    return ResponseEntity.ok(sportEventFacade.findAllSportEvents());
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/address/all")
  ResponseEntity<List<SportEventAddressDto>> getAllSportEventsAddresses() {
    return ResponseEntity.ok(sportEventFacade.findAllEventAddresses());
  }

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping("/details/{sportEventId}")
  ResponseEntity<SportEventDto> getSportEvent(@PathVariable Long sportEventId) {
    return ResponseEntity.ok(sportEventFacade.findSportEventById(sportEventId));
  }

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping("/address/details/{sportEventAddressId}")
  ResponseEntity<SportEventAddressDto> getSportEventAddress(@PathVariable Long sportEventAddressId) {
    return ResponseEntity.ok(sportEventFacade.findSportEventAddressById(sportEventAddressId));
  }

  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @GetMapping("/{eventType}")
  ResponseEntity<List<SportEventDto>> getEventSportByType(@PathVariable String eventType) {
    return ResponseEntity.ok(sportEventFacade.findAllSportEventsByType(eventType));
  }

  @GetMapping("/cleanup")
  @Hidden
  ResponseEntity<String> cleanup() {
    sportEventFacade.cleanup();
    return ResponseEntity.ok("sport event cleanup");
  }
}
