package tijo.sportEventApp.sportEvent.domain

import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification
import tijo.sportEventApp.utils.InstantProvider

class SportEventBase extends Specification {
  ApplicationEventPublisher eventPublisher = Stub(ApplicationEventPublisher.class)
  SportEventFacade sportEventFacade = new SportEventConfiguration().sportEventFacade(eventPublisher)
  InstantProvider instantProvider = new InstantProvider()
}
