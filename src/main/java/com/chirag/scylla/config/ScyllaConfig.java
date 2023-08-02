package com.chirag.scylla.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ScyllaConfig {

  @Value("${spring.profiles.active:#{null}}")
  private String activeProfile;

  @Bean
  public CqlSession cqlSession() {
    CqlSession cqlSession =
        CqlSession.builder()
            .withConfigLoader(DriverConfigLoader.fromClasspath(getConfFileName()))
            .build();
    log.info(
        "Scylla session has been created with keyspace {}",
        cqlSession.getKeyspace().map(ci -> ci.asCql(false)).orElse("NA"));
    return cqlSession;
  }

  private String getConfFileName() {
    String filename =
        "scylla".concat("-").concat(Optional.ofNullable(activeProfile).orElse("local"));
    log.info("scylla config file: {}", filename);
    return filename;
  }
}
