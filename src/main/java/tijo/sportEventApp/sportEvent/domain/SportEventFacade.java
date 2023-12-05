package tijo.sportEventApp.sportEvent.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto;
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto;
import tijo.sportEventApp.sportEvent.dto.SportEventDto;
import tijo.sportEventApp.sportEvent.exception.AlreadyReservedAddressException;
import tijo.sportEventApp.sportEvent.exception.NotExistingSportEventException;
import tijo.sportEventApp.utils.InstantProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventFacade {
  SportEventRepository sportEventRepository;
  SportEventAddressRepository sportEventAddressRepository;
  InstantProvider instantProvider;

  public SportEventAddressDto createEventAddress(CreateSportEventAddressDto createSportEventAddressDto) {
    return SportEventAddressDto.builder().build();
  }

  public List<SportEventAddressDto> findAllEventAddresses() {
    return new ArrayList<>();
  }

  public SportEventDto createSportEvent(CreateSportEventDto createSportEvent) {
    if (sportEventRepository.findBySportEventAddressAndEventTime(createSportEvent.getSportEventAddress(),
            createSportEvent.getEventTime()).isPresent()) {
      throw new AlreadyReservedAddressException("Address already reserved in given time");
    }
    SportEvent sportEvent = SportEvent.builder()
            .eventName(createSportEvent.getEventName())
            .eventTime(createSportEvent.getEventTime())
            .registrationDeadline(createSportEvent.getRegistrationDeadline())
            .description(createSportEvent.getDescription())
            .maxParticipants(createSportEvent.getMaxParticipants())
            .sportEventType(SportEventType.valueOf(createSportEvent.getSportEventType().name()))
            .sportEventAddress(createSportEvent.getSportEventAddress())
            .build();
    return sportEventRepository.save(sportEvent).dto();
  }

  public List<SportEventDto> findAllSportEvents() {
    return sportEventRepository.findAll().stream()
            .map(SportEvent::dto)
            .collect(Collectors.toList());
  }

  public void deleteSportEvent(Long sportEventId) {
    sportEventRepository.findBySportEventId(sportEventId)
            .orElseThrow(() -> new NotExistingSportEventException("There is no sport event with given id"));
    sportEventRepository.deleteById(sportEventId);
  }
}
