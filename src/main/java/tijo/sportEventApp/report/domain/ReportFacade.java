package tijo.sportEventApp.report.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReportFacade {
  ReportRepository reportRepository;
  SportEventAssignRepository sportEventAssignRepository;
}
