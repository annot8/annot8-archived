/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.components.capabilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.annot8.core.capabilities.GroupCapability;

public class SimpleGroupCapabilityTest {
  @Test
  public void testGetters() {
    GroupCapability gc = new SimpleGroupCapability("test");

    assertEquals("test", gc.getType());
  }

  @Test
  public void testEqualAndHashCode() {
    GroupCapability gc1 = new SimpleGroupCapability("test");
    GroupCapability gc2 = new SimpleGroupCapability("test");

    assertTrue(gc1.equals(gc2));
    assertFalse(gc1.equals("Hello world"));
    assertFalse(gc1.equals(123));

    assertEquals(gc1.hashCode(), gc2.hashCode());
  }
}
