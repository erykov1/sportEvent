package tijo.sportEventApp.configuration;

import org.testcontainers.containers.PostgreSQLContainer;

import java.time.Duration;

public class PostgresContainerConfig {
  private static PostgreSQLContainer postgreSQLContainer;

  public static void init() {
    postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(
        "postgres")
        .withDatabaseName("sport_event")
        .withUsername("postgres")
        .withPassword("test").withStartupTimeout(Duration.ofSeconds(600));
    postgreSQLContainer.start();
  }

  public static PostgreSQLContainer getPostgreSQLContainer() {
    return postgreSQLContainer;
  }
}
