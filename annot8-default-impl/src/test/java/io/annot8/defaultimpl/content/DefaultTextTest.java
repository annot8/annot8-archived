/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.annot8.testing.testimpl.TestConstants;
import io.annot8.testing.testimpl.TestItem;

public class DefaultTextTest {

  public void testBuilderFactory() {
    DefaultText.BuilderFactory factory = new DefaultText.BuilderFactory();
    assertNotNull(factory.create(new TestItem()));
  }

  @Test
  public void testBuilder() {
    DefaultText.Builder builder = new DefaultText.Builder();
    DefaultText defaultText =
        builder.create(TestConstants.CONTENT_ID, TestConstants.CONTENT_NAME, null, () -> "test");
    Assertions.assertEquals(TestConstants.CONTENT_ID, defaultText.getId());
    Assertions.assertEquals(TestConstants.CONTENT_NAME, defaultText.getName());
    Assertions.assertEquals("test", defaultText.getData());
  }
}
