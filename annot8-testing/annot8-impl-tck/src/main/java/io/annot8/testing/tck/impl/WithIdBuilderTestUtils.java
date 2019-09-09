/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.tck.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.helpers.WithId;
import io.annot8.core.helpers.builders.WithIdBuilder;
import io.annot8.core.helpers.builders.WithSave;

public class WithIdBuilderTestUtils<T extends WithIdBuilder<T> & WithSave<WithId>> {

  public void testWithIdBuilder(T builder) {
    WithId withId = null;
    try {
      withId = builder.withId("test").save();
    } catch (IncompleteException e) {
      fail("Test should not throw an exception here", e);
    }
    assertEquals("test", withId.getId());
  }
}
