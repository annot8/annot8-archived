/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

/** Unit tests for the default methods on the {@link Properties} interface. */
public class PropertiesTest extends PropertiesTestResources {

  @Test
  public void testHas() {
    TestProperties properties = new TestProperties(new HashMap<>());
    assertFalse(properties.has(TEST_KEY));

    TestProperties properties2 = new TestProperties(getPropertyMap());
    assertTrue(properties2.has(TEST_KEY));
    assertTrue(properties2.has(TEST_KEY, String.class));
    assertFalse(properties.has(TEST_KEY, Integer.class));
  }

  @Test
  public void testGet() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(TEST_VALUE, properties.get(TEST_KEY).get());
    assertFalse(properties.get("nonExistentKey").isPresent());
  }

  @Test
  public void testGetOrDefault() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(TEST_VALUE, properties.getOrDefault(TEST_KEY, DEFAULT_VALUE));
    assertEquals(DEFAULT_VALUE, properties.getOrDefault("nonExistentKey", DEFAULT_VALUE));
  }

  @Test
  public void testGetProperty() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(TEST_VALUE, properties.get(TEST_KEY, String.class).get());
    assertFalse(properties.get("nonExistentKey", String.class).isPresent());
    assertFalse(properties.get(TEST_KEY, Integer.class).isPresent());
  }

  @Test
  public void testGetObjectOrDefault() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(TEST_VALUE, properties.getObjectOrDefault(TEST_KEY, DEFAULT_VALUE));
    assertEquals(DEFAULT_VALUE, properties.getObjectOrDefault("nonExistentKey", DEFAULT_VALUE));
  }

  @Test
  public void testKeys() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(1, properties.keys().count());
    assertEquals(TEST_KEY, properties.keys().findFirst().get());
  }

  @Test
  public void testListKeys() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(1, properties.listKeys(String.class).count());
    assertEquals(TEST_KEY, properties.listKeys(String.class).findFirst().get());
    assertEquals(0, properties.listKeys(Integer.class).count());
  }

  @Test
  public void testGetAll() {
    TestProperties properties = new TestProperties(getPropertyMap());
    assertEquals(1, properties.getAll(String.class).size());
    assertEquals(TEST_VALUE, properties.getAll(String.class).get(TEST_KEY));
    assertEquals(0, properties.getAll(Integer.class).size());
  }
}
