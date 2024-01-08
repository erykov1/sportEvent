package tijo.sportEventApp.report.domain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tijo.sportEventApp.utils.InstantProvider;

@Configuration
class ReportConfiguration {
  @Bean
  ReportFacade reportFacade(ReportRepository reportRepository, SportEventAssignRepository sportEventAssignRepository) {
    return ReportFacade.builder()
            .reportRepository(reportRepository)
            .sportEventAssignRepository(sportEventAssignRepository)
            .instantProvider(new InstantProvider())
            .build();
  }

  ReportFacade reportFacade() {
    return ReportFacade.builder()
            .reportRepository(new InMemoryReportRepository())
            .sportEventAssignRepository(new InMemorySportEventAssignRepository())
            .instantProvider(new InstantProvider())
            .build();
  }

}
