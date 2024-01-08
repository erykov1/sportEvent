package tijo.sportEventApp.sportEvent.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.report.dto.SportEventAssignDto;
import tijo.sportEventApp.sportEvent.dto.*;
import tijo.sportEventApp.sportEvent.exception.AlreadyReservedAddressException;
import tijo.sportEventApp.sportEvent.exception.NotExistingSportEventException;
import tijo.sportEventApp.utils.InstantProvider;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventFacade {
  SportEventRepository sportEventRepository;
  SportEventAddressRepository sportEventAddressRepository;
  SportEventPublisher sportEventPublisher;

  public SportEventAddressDto createEventAddress(CreateSportEventAddressDto createSportEventAddress) {
    Optional<SportEventAddress> address = sportEventAddressRepository.findSportEventAddressByDetails(createSportEventAddress.getPostalCode(),
        createSportEventAddress.getCity(), createSportEventAddress.getStreet(), createSportEventAddress.getStreetNumber());
    if (address.isPresent()) {
      return address.get().dto();
    } else {
      SportEventAddress sportEventAddressSave = SportEventAddress.builder()
          .postalCode(createSportEventAddress.getPostalCode())
          .city(createSportEventAddress.getCity())
          .street(createSportEventAddress.getStreet())
          .streetNumber(createSportEventAddress.getStreetNumber())
          .build();
      return sportEventAddressRepository.save(sportEventAddressSave).dto();
    }
  }

  public List<SportEventAddressDto> findAllEventAddresses() {
    return sportEventAddressRepository.findAll().stream()
            .map(SportEventAddress::dto)
            .collect(Collectors.toList());
  }

  public SportEventDto findSportEventById(Long sportEventId) {
    return sportEventRepository.findBySportEventId(sportEventId)
        .orElseThrow(() -> new NotExistingSportEventException("Sport event does not exist")).dto();
  }

  public SportEventAddressDto findSportEventAddressById(Long sportEventAddressId) {
    return sportEventAddressRepository.findById(sportEventAddressId)
        .orElseThrow(() -> new NotExistingSportEventException("Sport event address does not exist")).dto();
  }

  public List<SportEventDto> findAllSportEventsByType(String eventType) {
    return sportEventRepository.findAll().stream()
        .filter(report -> report.dto().getSportEventType().name().equals(eventType))
        .map(SportEvent::dto)
        .collect(Collectors.toList());
  }

  public SportEventDto createSportEvent(CreateSportEventDto createSportEvent) {
    checkIfAlreadyReserved(createSportEvent);
    SportEvent sportEvent = SportEvent.builder()
            .eventName(createSportEvent.getEventName())
            .eventTime(InstantProvider.fromFormatted(createSportEvent.getEventTime()))
            .registrationDeadline(InstantProvider.fromFormatted(createSportEvent.getRegistrationDeadline()))
            .description(createSportEvent.getDescription())
            .maxParticipants(createSportEvent.getMaxParticipants())
            .sportEventType(SportEventType.valueOf(createSportEvent.getSportEventType().name()))
            .sportEventAddress(createSportEvent.getSportEventAddress())
            .build();
    emmitEvent(sportEvent);
    return sportEventRepository.save(sportEvent).dto();
  }

  public List<SportEventDto> findAllSportEvents() {
    return sportEventRepository.findAll().stream()
            .map(SportEvent::dto)
            .collect(Collectors.toList());
  }

  private void checkIfAlreadyReserved(CreateSportEventDto createSportEvent) {
    if (sportEventRepository.findBySportEventAddressAndEventTime(createSportEvent.getSportEventAddress(),
            InstantProvider.fromFormatted(createSportEvent.getEventTime())).isPresent()) {
      throw new AlreadyReservedAddressException("Address has been already reserved");
    }
  }

  private void emmitEvent(SportEvent sportEvent) {
    SportEventAssignDto sportEventPublish = SportEventAssignDto.builder()
        .sportEventId(sportEvent.dto().getSportEventId())
        .maxParticipants(sportEvent.dto().getMaxParticipants())
        .registrationDeadline(sportEvent.dto().getRegistrationDeadline())
        .eventTime(sportEvent.dto().getEventTime())
        .build();
    sportEventPublisher.notifySportEventCreated(sportEventPublish);
  }

  public void cleanup() {
    sportEventRepository.deleteAll();
    sportEventAddressRepository.deleteAll();
  }
}