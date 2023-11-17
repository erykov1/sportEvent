package tijo.sportEventApp.report.domain;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReportConfiguration {
  @Bean
  ReportFacade reportFacade(ReportRepository reportRepository) {
    return new ReportFacade(reportRepository);
  }

}
