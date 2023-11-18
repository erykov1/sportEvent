package tijo.sportEventApp.report.domain;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportFacade {
  ReportRepository reportRepository;

  @Autowired
  public ReportFacade(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
  }
}
