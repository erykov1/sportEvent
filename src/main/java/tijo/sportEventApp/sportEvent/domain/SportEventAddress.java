package tijo.sportEventApp.sportEvent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import tijo.sportEventApp.sportEvent.dto.SportEventAddressDto;

@Entity
@Table(name = "sport_events_addresses")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEventAddress {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long eventAddressId;
  String postalCode;
  String city;
  String street;
  String streetNumber;

  SportEventAddressDto dto() {
    return SportEventAddressDto.builder()
            .eventAddressId(eventAddressId)
            .postalCode(postalCode)
            .city(city)
            .street(street)
            .streetNumber(streetNumber)
            .build();
  }
}
