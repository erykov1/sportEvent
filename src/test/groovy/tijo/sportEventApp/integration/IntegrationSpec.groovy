package tijo.sportEventApp.integration

import configuration.PostgresContainerConfig
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner.class)
@Testcontainers
abstract class IntegrationSpec extends Specification {
  static {
    PostgresContainerConfig.init()
    System.setProperty("spring.datasource.url", PostgresContainerConfig.getPostgreSQLContainer().getJdbcUrl())
    System.setProperty("spring.datasource.username", PostgresContainerConfig.getPostgreSQLContainer().getUsername())
    System.setProperty("spring.datasource.password", PostgresContainerConfig.getPostgreSQLContainer().getPassword())
    System.setProperty("INTEGRATION", "true")
  }

  @Autowired
  protected WebApplicationContext context

  private MockMvc mockMvc

  @Autowired
  private ObjectMapper objectMapper

  SportEventAppApi api

  def setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
      .build()
    api = new SportEventAppApi(mockMvc, objectMapper)
  }
}
