/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.components.capabilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import io.annot8.common.data.content.Text;
import io.annot8.core.capabilities.ContentCapability;

public class SimpleContentCapabilityTest {
  @Test
  public void testGetters() {
    ContentCapability cc = new SimpleContentCapability(Text.class);

    assertEquals(Text.class, cc.getType());
  }

  @Test
  public void testEqualAndHashCode() {
    ContentCapability cc1 = new SimpleContentCapability(Text.class);
    ContentCapability cc2 = new SimpleContentCapability(Text.class);

    assertTrue(cc1.equals(cc2));
    assertFalse(cc1.equals("Hello world"));
    assertFalse(cc1.equals(123));

    assertEquals(cc1.hashCode(), cc2.hashCode());
  }
}
