package tijo.sportEventApp.report.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemorySportEventAssignRepository implements SportEventAssignRepository {
  private Map<Long, SportEventAssign> table = new ConcurrentHashMap<>();

  @Override
  public void flush() {

  }

  @Override
  public <S extends SportEventAssign> S saveAndFlush(S entity) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> List<S> saveAllAndFlush(Iterable<S> entities) {
    return null;
  }

  @Override
  public void deleteAllInBatch(Iterable<SportEventAssign> entities) {

  }

  @Override
  public void deleteAllByIdInBatch(Iterable<Long> longs) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public SportEventAssign getOne(Long aLong) {
    return null;
  }

  @Override
  public SportEventAssign getById(Long aLong) {
    return null;
  }

  @Override
  public SportEventAssign getReferenceById(Long aLong) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends SportEventAssign> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends SportEventAssign> boolean exists(Example<S> example) {
    return false;
  }

  @Override
  public <S extends SportEventAssign, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> S save(S entity) {
    return null;
  }

  @Override
  public <S extends SportEventAssign> List<S> saveAll(Iterable<S> entities) {
    return null;
  }

  @Override
  public Optional<SportEventAssign> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public List<SportEventAssign> findAll() {
    return null;
  }

  @Override
  public List<SportEventAssign> findAllById(Iterable<Long> longs) {
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
  public void delete(SportEventAssign entity) {

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> longs) {

  }

  @Override
  public void deleteAll(Iterable<? extends SportEventAssign> entities) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public List<SportEventAssign> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<SportEventAssign> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public Optional<SportEventAssign> findSportEventBySportEventId(Long sportEventId) {
    return table.values().stream()
        .filter(sportEvent -> sportEvent.dto().getSportEventId().equals(sportEventId))
        .findFirst();
  }
}
