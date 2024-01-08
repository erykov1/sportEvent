package tijo.sportEventApp.sportEvent.domain

import spock.lang.Unroll
import tijo.sportEventApp.sportEvent.dto.CreateSportEventAddressDto
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto

class SportEventAddressSpec extends SportEventBase implements SportEventAddressSample {
  def "Should create new event address"() {
    when: "creates new event address"
      SportEventAddressDto result = sportEventFacade.createEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    then: "new event address is created"
      equalsEventAddress(result, createEventAddress(eventAddressId: result.eventAddressId, postalCode: "30-100",
          city: "Tarnow", street: "Mickiewicza", streetNumber: "34A"
      ))
  }

  def "Should not create event address if it already exists"() {
    given: "there is event address"
      SportEventAddressDto result = sportEventFacade.createEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    when: "creates the same event address"
      sportEventFacade.createEventAddress(CreateSportEventAddressDto.builder()
          .postalCode("30-100").city("Tarnow").street("Mickiewicza").streetNumber("34A").build()
      )
    then: "there is only one event address"
      equalsEventAddresses(sportEventFacade.findAllEventAddresses(), [createEventAddress(eventAddressId: result.eventAddressId,
          postalCode: "30-100", city: "Tarnow", street: "Mickiewicza", streetNumber: "34A"
      )])
  }

  @Unroll
  def "Should create event address"() {
    when: "creates event address with the same fields but another #DIFFERENT_FIELD"
      SportEventAddressDto result = sportEventFacade.createEventAddress(CreateSportEventAddressDto.builder()
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
}
