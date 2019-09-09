/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.properties;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TestProperties implements MutableProperties {

  private final Map<String, Object> props;

  public TestProperties(Map<String, Object> props) {
    this.props = new ConcurrentHashMap<>();
    this.props.putAll(props);
  }

  @Override
  public Map<String, Object> getAll() {
    return props;
  }

  @Override
  public void set(String key, Object value) {
    props.put(key, value);
  }

  @Override
  public Optional<Object> remove(String key) {
    if (props.containsKey(key)) {
      return Optional.of(props.remove(key));
    }
    return Optional.empty();
  }
}
