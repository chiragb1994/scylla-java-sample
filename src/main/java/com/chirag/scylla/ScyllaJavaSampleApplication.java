package com.chirag.scylla;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@OpenAPIDefinition(
    info = @Info(title = "Scylla Java Sample", description = "Scylla sample swagger"),
    servers = @Server(url = "/scylla-java-sample", description = "Default Server URL"))
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ScyllaJavaSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ScyllaJavaSampleApplication.class, args);
  }
}
