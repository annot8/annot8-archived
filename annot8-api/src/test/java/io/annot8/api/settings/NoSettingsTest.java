/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.settings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NoSettingsTest {

  @Test
  void getInstance() {
    assertNotNull(NoSettings.getInstance());
  }

  @Test
  void validate() {
    assertTrue(NoSettings.getInstance().validate());
  }
}
