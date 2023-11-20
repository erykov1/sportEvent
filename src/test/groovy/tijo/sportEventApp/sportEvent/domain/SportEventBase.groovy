package tijo.sportEventApp.sportEvent.domain

import spock.lang.Specification
import tijo.sportEventApp.utils.InstantProvider

class SportEventBase extends Specification {
  SportEventFacade sportEventFacade = new SportEventConfiguration().sportEventFacade()
  InstantProvider instantProvider = new InstantProvider()
}
