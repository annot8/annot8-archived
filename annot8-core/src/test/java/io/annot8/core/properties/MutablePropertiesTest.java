/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class MutablePropertiesTest extends PropertiesTestResources {

  @Test
  public void testSet() {
    MutableProperties properties = new TestProperties(getPropertyMap());
    String replacementKey = "replacementKey";
    String replacementValue = "replacementValue";
    Map<String, Object> replacement = new HashMap<>();
    replacement.put(replacementKey, replacementValue);

    properties.set(replacement);
    assertEquals(1, properties.getAll().size());
    assertTrue(properties.has(replacementKey));
    assertFalse(properties.has(TEST_KEY));
    assertEquals(replacementValue, properties.get(replacementKey).get());
  }

  @Test
  public void testRemoveAll() {
    MutableProperties properties = new TestProperties(getPropertyMap());
    assertEquals(1, properties.getAll().size());

    properties.removeAll();
    assertEquals(0, properties.getAll().size());
    assertFalse(properties.has(TEST_KEY));
  }

  @Test
  public void testAdd() {
    String newKey = "newKey";
    String newValue = "newValue";
    Map<String, Object> newProperties = new HashMap<>();
    newProperties.put(newKey, newValue);

    MutableProperties properties = new TestProperties(getPropertyMap());
    properties.add(newProperties);
    assertEquals(2, properties.getAll().size());
    assertEquals(TEST_VALUE, properties.get(TEST_KEY).get());
    assertEquals(newValue, properties.get(newKey).get());
  }

  @Test
  public void testRemove() {
    MutableProperties properties = new TestProperties(getPropertyMap());
    List<String> nonExistentKeys = new ArrayList<>();
    nonExistentKeys.add("nonExistentKey");

    properties.remove(nonExistentKeys);
    assertEquals(1, properties.getAll().size());

    List<String> existingKeys = new ArrayList<>();
    existingKeys.add(TEST_KEY);

    properties.remove(existingKeys);
    assertEquals(0, properties.getAll().size());
  }
}
