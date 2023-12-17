package tijo.sportEventApp.sportEvent.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.report.dto.SportEventAssignDeleteDto;
import tijo.sportEventApp.report.dto.SportEventAssignDto;
import tijo.sportEventApp.sportEvent.dto.*;
import tijo.sportEventApp.sportEvent.exception.AlreadyReservedAddressException;
import tijo.sportEventApp.sportEvent.exception.NotExistingSportEventException;
import tijo.sportEventApp.utils.InstantProvider;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SportEventFacade {
  SportEventRepository sportEventRepository;
  SportEventAddressRepository sportEventAddressRepository;
  InstantProvider instantProvider;
  SportEventPublisher sportEventPublisher;

  public SportEventAddressDto createEventAddress(CreateSportEventAddressDto createSportEventAddress) {
    if (sportEventAddressRepository.findSportEventAddressByDetails(createSportEventAddress.getPostalCode(),
            createSportEventAddress.getCity(), createSportEventAddress.getStreet(), createSportEventAddress.getStreetNumber()
    ).isPresent()) {
      return SportEventAddressDto.builder().build();
    }
    SportEventAddress sportEventAddressSave = SportEventAddress.builder()
            .postalCode(createSportEventAddress.getPostalCode())
            .city(createSportEventAddress.getCity())
            .street(createSportEventAddress.getStreet())
            .streetNumber(createSportEventAddress.getStreetNumber())
            .build();
    return sportEventAddressRepository.save(sportEventAddressSave).dto();
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
            .eventTime(createSportEvent.getEventTime())
            .registrationDeadline(createSportEvent.getRegistrationDeadline())
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

  public void cleanup() {
    sportEventRepository.deleteAll();
    sportEventAddressRepository.deleteAll();
  }

  private void checkIfAlreadyReserved(CreateSportEventDto createSportEvent) {
    if (sportEventRepository.findBySportEventAddressAndEventTime(createSportEvent.getSportEventAddress(),
            createSportEvent.getEventTime()).isPresent()) {
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

  private void emmitDeleteEvent(SportEventAssignDeleteDto sportEventDelete) {
    sportEventPublisher.notifySportEventDeleted(sportEventDelete);
  }
}