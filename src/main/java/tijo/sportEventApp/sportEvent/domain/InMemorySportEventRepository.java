package tijo.sportEventApp.sportEvent.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemorySportEventRepository implements SportEventRepository {
  private Map<Long, SportEvent> table = new ConcurrentHashMap<>();

  @Override
  public void flush() {

  }

  @Override
  public <S extends SportEvent> S saveAndFlush(S entity) {
    return null;
  }

  @Override
  public <S extends SportEvent> List<S> saveAllAndFlush(Iterable<S> entities) {
    return null;
  }

  @Override
  public void deleteAllInBatch(Iterable<SportEvent> entities) {

  }

  @Override
  public void deleteAllByIdInBatch(Iterable<Long> longs) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public SportEvent getOne(Long aLong) {
    return null;
  }

  @Override
  public SportEvent getById(Long aLong) {
    return null;
  }

  @Override
  public SportEvent getReferenceById(Long aLong) {
    return null;
  }

  @Override
  public <S extends SportEvent> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends SportEvent> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends SportEvent> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends SportEvent> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends SportEvent> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends SportEvent> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends SportEvent, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }

  @Override
  public <S extends SportEvent> S save(S entity) {
    return null;
  }

  @Override
  public <S extends SportEvent> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<SportEvent> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public List<SportEvent> findAll() {
    return null;
  }

  @Override
  public List<SportEvent> findAllById(Iterable<Long> longs) {
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
  public void delete(SportEvent entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends SportEvent> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<SportEvent> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<SportEvent> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public Optional<SportEvent> findBySportEventId(Long sportEventId) {
    return Optional.empty();
  }

  @Override
  public Optional<SportEvent> findBySportEventAddressAndEventTime(Long sportEventAddress, Instant eventTime) {
    return Optional.empty();
  }
}
