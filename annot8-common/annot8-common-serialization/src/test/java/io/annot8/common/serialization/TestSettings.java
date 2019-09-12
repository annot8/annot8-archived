package io.annot8.common.serialization;

import io.annot8.core.settings.Settings;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class TestSettings implements Settings {
  private final String host;
  private final int port;

  @JsonbCreator
  public TestSettings(@JsonbProperty("host") String host, @JsonbProperty("port") int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public boolean validate() {
    return host != null && !host.isBlank()
        && port >= 0 && port <= 65535;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }
}
