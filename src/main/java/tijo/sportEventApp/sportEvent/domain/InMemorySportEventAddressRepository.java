package tijo.sportEventApp.sportEvent.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemorySportEventAddressRepository implements SportEventAddressRepository {
  private Map<Long, SportEventAddress> table = new ConcurrentHashMap<>();

  @Override
  public void flush() {

  }

  @Override
  public <S extends SportEventAddress> S saveAndFlush(S entity) {
    return null;
  }

  @Override
  public <S extends SportEventAddress> List<S> saveAllAndFlush(Iterable<S> entities) {
    return null;
  }

  @Override
  public void deleteAllInBatch(Iterable<SportEventAddress> entities) {

  }

  @Override
  public void deleteAllByIdInBatch(Iterable<Long> longs) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public SportEventAddress getOne(Long aLong) {
    return null;
  }

  @Override
  public SportEventAddress getById(Long aLong) {
    return null;
  }

  @Override
  public SportEventAddress getReferenceById(Long aLong) {
    return null;
  }

  @Override
  public <S extends SportEventAddress> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends SportEventAddress> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends SportEventAddress> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends SportEventAddress> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends SportEventAddress> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends SportEventAddress> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends SportEventAddress, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }

  @Override
  public SportEventAddress save(SportEventAddress entity) {
    if (entity.dto().getEventAddressId() == null) {
      Long eventAddressId = new Random().nextLong();
      entity = entity.toBuilder()
          .eventAddressId(eventAddressId)
          .build();
    }
    table.put(entity.dto().getEventAddressId(), entity);
    return entity;
  }

  @Override
  public <S extends SportEventAddress> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<SportEventAddress> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public List<SportEventAddress> findAll() {
    return table.values().stream().toList();
  }

  @Override
  public List<SportEventAddress> findAllById(Iterable<Long> longs) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(SportEventAddress entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends SportEventAddress> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<SportEventAddress> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<SportEventAddress> findAll(Pageable pageable) {
    return null;
  }


  @Override
  public Optional<SportEventAddress> findSportEventAddressByDetails(String postalCode, String city, String street, String streetNumber) {
    return table.values().stream()
        .filter(address -> address.dto().getPostalCode().equals(postalCode))
        .filter(address -> address.dto().getCity().equals(city))
        .filter(address -> address.dto().getStreet().equals(street))
        .filter(address -> address.dto().getStreetNumber().equals(streetNumber))
        .findFirst();
  }
}
