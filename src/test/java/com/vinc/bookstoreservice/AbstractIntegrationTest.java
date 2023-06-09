package com.vinc.bookstoreservice;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@Testcontainers
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Container
        static JdbcDatabaseContainer postgres = new PostgreSQLContainer("postgres:14")
                .withDatabaseName("book_store_test_db")
                .withInitScript("db/init-book-store-test-db.sql");

        public static Map<String, String> getProperties() {
            Startables.deepStart(Stream.of(postgres)).join();

            return Map.of(
                    "spring.r2dbc.url", "r2dbc:postgresql://"
                            + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                            + "/" + postgres.getDatabaseName(),
                    "spring.r2dbc.username", postgres.getUsername(),
                    "spring.r2dbc.password", postgres.getPassword(),
                    "spring.liquibase.url", "jdbc:postgresql://"
                            + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                            + "/" + postgres.getDatabaseName(),
                    "spring.liquibase.user", postgres.getUsername(),
                    "spring.liquibase.password", postgres.getPassword()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext context) {
            var env = context.getEnvironment();
            env.getPropertySources().addFirst(new MapPropertySource(
                    "testcontainers", (Map) getProperties()
            ));
        }
    }
}
