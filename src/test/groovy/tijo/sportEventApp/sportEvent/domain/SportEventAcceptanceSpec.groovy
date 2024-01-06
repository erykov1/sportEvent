package tijo.sportEventApp.sportEvent.domain

import spock.lang.Unroll
import tijo.sportEventApp.integration.IntegrationSpec
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.SportEventDto
import tijo.sportEventApp.sportEvent.dto.SportEventTypeDto
import tijo.sportEventApp.utils.InstantProvider

class SportEventAcceptanceSpec extends IntegrationSpec implements SportEventSample, SportEventAddressSample {
  def "Should create new sport event"() {
    when: "Creates new sport event"
    SportEventDto result = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    then: "sport event is created"
      equalsSportEvents([result], [createSportEvent(sportEventId: result.sportEventId, eventName: "event",
          eventTime: InstantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2024-07-08 12:00"), description: "event desc", eventAddress: EVENT_ADDRESS,
          maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL
      )])
  }

  def "Should create new sport address"() {
    when: "Creates new sport event address"
      SportEventAddressDto result = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    then: "sport event address is created"
      equalsEventAddresses([result], [createEventAddress(eventAddressId: result.eventAddressId,
          postalCode: "30-100", city: "Tarnow", street: "Mickiewicza", streetNumber: "34A"
      )])
  }

  def "Should create only one sport event address with the same data"() {
    given: "Creates new sport event address"
    SportEventAddressDto result = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    when: "Creates the same sport event address"
      api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    then: "There is only one address"
      equalsEventAddresses(api.sportEvent().allSportEventsAddresses, [createEventAddress(eventAddressId: result.eventAddressId,
          postalCode: "30-100", city: "Tarnow", street: "Mickiewicza", streetNumber: "34A"
      )])
  }

  @Unroll
  def "Should create event address"() {
    when: "creates event address with the same fields but another #DIFFERENT_FIELD"
      SportEventAddressDto result = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode(POSTAL_CODE).city(CITY).street(STREET).streetNumber(STREET_NUMBER).build()
      )
    then: "event address is created"
      equalsEventAddress(result, createEventAddress(eventAddressId: result.eventAddressId, postalCode: POSTAL_CODE,
          city: CITY, street: STREET, streetNumber: STREET_NUMBER
      ))
    where:
      POSTAL_CODE | CITY     | STREET        | STREET_NUMBER
      "33-100"    | "Krakow" | "Mickiewicza" | "34A"
      "33-100"    | "Krakow" | "Mickiewicza" | "34B"
      "33-100"    | "Krakow" | "Moscickiego" | "34A"
      "33-101"    | "Krakow" | "Mickiewicza" | "34A"
  }

  def "Should get all sport events"() {
    given: "there are some sport events"
    SportEventDto handball = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.HANDBALL))
    SportEventDto football = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
        eventTime: "2025-08-08 12:00", registrationDeadline: "2025-07-08 12:00",
        description: "event desc", eventAddress: 1L, maxParticipants: 200L, sportEventType: SportEventTypeDto.FOOTBALL))
    when: "ask for all sport events"
      def result = api.sportEvent().getAllSportEvents()
    then: "gets all sport events"
    equalsSportEvents(result, [createSportEvent(sportEventId: handball.sportEventId, eventName: "event",
        eventTime: InstantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2024-07-08 12:00"),
        description: "event desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.HANDBALL
    ), createSportEvent(sportEventId: football.sportEventId, eventName: "event",
        eventTime: InstantProvider.fromFormatted("2025-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2025-07-08 12:00"),
        description: "event desc", eventAddress: EVENT_ADDRESS,
        maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.FOOTBALL,
    )])
  }

  def "Should get all sport events addresses"() {
    given: "there are some sport events addresses"
      SportEventAddressDto tarnow = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build())
      SportEventAddressDto cracow = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
        .postalCode("30-100").city("Krakow").street("Mickiewicza").streetNumber("34A").build())
    when: "ask for all addresses"
      def result = api.sportEvent().getAllSportEventsAddresses()
    then: "gets all sport event addresses"
      equalsEventAddresses(result, [createEventAddress(eventAddressId: cracow.eventAddressId, postalCode: "30-100",
        city: "Krakow", street: "Mickiewicza", streetNumber: "34A"
      ), createEventAddress(eventAddressId: tarnow.eventAddressId, postalCode: "30-100",
          city: "Tarnow", street: "Mickiewicza", streetNumber: "34A")])
  }

  @Unroll
  def "Should get sport event by #EVENT_TYPE"() {
    given: "there is address"
      SportEventAddressDto tarnow = api.sportEvent().createSportEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build())
    and: "there is sport event with type $EVENT_TYPE"
      SportEventDto sportEvent = api.sportEvent().createSportEvent(createNewSportEvent(eventName: "event",
          eventTime: "2024-08-08 12:00", registrationDeadline: "2024-07-08 12:00",
          description: "event desc", eventAddress: tarnow.eventAddressId, maxParticipants: 200L, sportEventType: SportEventTypeDto.valueOf(EVENT_TYPE)))
    when: "asks for sport event by type $EVENT_TYPE"
      def result = api.sportEvent().getAllSportEventsByType(SportEventTypeDto.valueOf(EVENT_TYPE).toString())
    then: "returns sport event by type"
      equalsSportEvents(result, [createSportEvent(sportEventId: sportEvent.sportEventId, eventName: "event",
          eventTime: InstantProvider.fromFormatted("2024-08-08 12:00"), registrationDeadline: InstantProvider.fromFormatted("2024-07-08 12:00"),
          description: "event desc", eventAddress: tarnow.eventAddressId,
          maxParticipants: MAX_PARTICIPANTS, sportEventType: SportEventTypeDto.valueOf(EVENT_TYPE)
      )])
    where:
      EVENT_TYPE << ['VOLLEYBALL', 'HANDBALL', 'FOOTBALL', 'TENNIS', 'MARATHON', 'RUNNING']
  }

  def cleanup() {
    api.user().cleanup()
    api.sportEvent().cleanup()
    api.report().cleanup()
  }
}
