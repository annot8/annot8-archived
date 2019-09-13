/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.properties;

import java.util.HashMap;
import java.util.Map;

public class PropertiesTestResources {

  protected static final String TEST_KEY = "KEY";
  protected static final String TEST_VALUE = "VALUE";
  protected static final String DEFAULT_VALUE = "DEFAULT_VALUE";

  protected Map<String, Object> getPropertyMap() {
    Map<String, Object> props = new HashMap<>();
    props.put(TEST_KEY, TEST_VALUE);
    return props;
  }
}
