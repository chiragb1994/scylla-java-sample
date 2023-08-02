package com.chirag.scylla.config;

import com.datastax.oss.driver.api.core.auth.PlainTextAuthProviderBase;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.config.DriverExecutionProfile;
import com.datastax.oss.driver.api.core.context.DriverContext;
import com.datastax.oss.driver.api.core.metadata.EndPoint;
import org.springframework.lang.NonNull;

public class ScyllaEnvironmentAuth extends PlainTextAuthProviderBase {

  private final DriverExecutionProfile config;

  public ScyllaEnvironmentAuth(DriverContext context) {
    super(context.getSessionName());
    this.config = context.getConfig().getDefaultProfile();
  }

  @NonNull
  @Override
  protected Credentials getCredentials(
      @NonNull EndPoint endPoint, @NonNull String serverAuthenticator) {
    return new Credentials(
        System.getenv(config.getString(DefaultDriverOption.AUTH_PROVIDER_USER_NAME)).toCharArray(),
        System.getenv(config.getString(DefaultDriverOption.AUTH_PROVIDER_PASSWORD)).toCharArray());
  }
}
