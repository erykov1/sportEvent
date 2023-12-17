package tijo.sportEventApp.sportEvent;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SportEventController {
  SportEventFacade sportEventFacade;

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
}
