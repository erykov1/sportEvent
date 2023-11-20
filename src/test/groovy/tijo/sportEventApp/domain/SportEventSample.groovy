package tijo.sportEventApp.sportEvent.domain

import tijo.sportEventApp.sportEvent.dto.CreateSportEventDto
import tijo.sportEventApp.sportEvent.dto.SportEventDto
import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto
import tijo.sportEventApp.utils.InstantProvider

import java.time.Instant
import java.util.stream.Collectors

trait SportEventSample {
  static Instant eventTime = InstantProvider.fromFormatted('2024-12-08 12:00')
  static Instant registrationDeadline = InstantProvider.fromFormatted("2024-08-05 12:00")
  static Long EVENT_ADDRESS = 1L
  static Long MAX_PARTICIPANTS = 200L

  private Map<String, Object> DEFAULT_SPORT_EVENT_DATA = [
      sportEventId        : 1L,
      eventName           : '',
      eventTime           : eventTime,
      registrationDeadline: registrationDeadline,
      description         : '',
      eventAddress        : EVENT_ADDRESS,
      maxParticipants     : MAX_PARTICIPANTS,
      sportEventType      : ''
  ] as Map<String, Object>

  SportEventDto createSportEvent(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_SPORT_EVENT_DATA + changes
    SportEventDto.builder()
        .sportEventId(changesWithDefaults.sportEventId as Long)
        .eventName(changesWithDefaults.eventName as String)
        .eventTime(changesWithDefaults.eventTime as Instant)
        .registrationDeadline(changesWithDefaults.registrationDeadline as Instant)
        .description(changesWithDefaults.description as String)
        .eventAddress(changesWithDefaults.eventAddress as Long)
        .maxParticipants(changesWithDefaults.maxParticipants as Long)
        .sportEventType(changesWithDefaults.sportEventType as SportEventTypeDto)
        .build()
  }

  private Map<String, Object> DEFAULT_NEW_SPORT_EVENT_DATA = [
      eventName           : '',
      eventTime           : eventTime,
      registrationDeadline: registrationDeadline,
      description         : '',
      eventAddress        : EVENT_ADDRESS,
      maxParticipants     : MAX_PARTICIPANTS,
      sportEventType      : SportEventTypeDto.HANDBALL
  ] as Map<String, Object>

  CreateSportEventDto createNewSportEvent(Map<String, Object> changes = [:]) {
    def changesWithDefaults = DEFAULT_NEW_SPORT_EVENT_DATA + changes
    CreateSportEventDto.builder()
        .eventName(changesWithDefaults.eventName as String)
        .eventTime(changesWithDefaults.eventTime as Instant)
        .registrationDeadline(changesWithDefaults.registrationDeadline as Instant)
        .description(changesWithDefaults.description as String)
        .eventAddress(changesWithDefaults.eventAddress as Long)
        .maxParticipants(changesWithDefaults.maxParticipants as Long)
        .sportEventType(changesWithDefaults.sportEventType as SportEventTypeDto)
        .build()
  }

  private List<SportEventDto> buildSportEvents(List<Map<String, Object>> sportEvents) {
    sportEvents.stream().map({ createSportEvent(it) }).collect(Collectors.toList())
  }

  void equalsSportEvents(List<SportEventDto> sportEvents, List<SportEventDto> expected) {
    def comparator = Comparator.comparing(SportEventDto::getSportEventId)
        .thenComparing(Comparator.comparing(SportEventDto::getEventName))
    sportEvents.sort(comparator)
    assert sportEvents.sportEventId == expected.sportEventId
    assert sportEvents.eventName == expected.eventName
    assert sportEvents.eventTime == expected.eventTime
    assert sportEvents.registrationDeadline == expected.registrationDeadline
    assert sportEvents.description == expected.description
    assert sportEvents.eventAddress == expected.eventAddress
    assert sportEvents.maxParticipants == expected.maxParticipants
    assert sportEvents.sportEventType == expected.sportEventType
  }

}