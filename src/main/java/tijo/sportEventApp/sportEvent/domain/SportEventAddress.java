package tijo.sportEventApp.sportEvent.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "sport_events_addresses")
@Builder()
@FieldDefaults(level = AccessLevel.PRIVATE)
class SportEventAddress {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long eventAddressId;
  String postalCode;
  String city;
  String street;
  String streetNumber;
}
