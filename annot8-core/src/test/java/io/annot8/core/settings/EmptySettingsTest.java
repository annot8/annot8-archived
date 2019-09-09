/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.settings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmptySettingsTest {

  @Test
  void getInstance() {
    assertNotNull(EmptySettings.getInstance());
  }

  @Test
  void validate() {
    assertTrue(EmptySettings.getInstance().validate());
  }
}
