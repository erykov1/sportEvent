package tijo.sportEventApp.integration

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import tijo.sportEventApp.configuration.ObjectMapperConfiguration
import spock.lang.Specification
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.context.junit4.SpringRunner
import tijo.sportEventApp.report.ReportController
import tijo.sportEventApp.report.domain.ReportFacade

@SpringBootTest
@RunWith(SpringRunner.class)
abstract class IntegrationSpec extends Specification {
  private MockMvc mockMvc

  @Autowired
  private ObjectMapper objectMapper = ObjectMapperConfiguration.createObjectMapper();

  @Autowired
  private ReportFacade reportFacade

  SportEventAppApi api

  def setup() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(new ReportController(reportFacade))
        .build();
    api = new SportEventAppApi(mockMvc, objectMapper);
  }
}
