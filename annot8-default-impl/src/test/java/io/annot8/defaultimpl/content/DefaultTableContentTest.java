/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.annot8.common.data.content.Table;
import io.annot8.common.data.content.TableContent;
import io.annot8.core.data.Content.Builder;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.defaultimpl.content.DefaultTableContent.BuilderFactory;
import io.annot8.testing.testimpl.TestItem;

public class DefaultTableContentTest {

  @Test
  public void testBuilder() {
    BuilderFactory factory = new BuilderFactory();
    Builder<TableContent, Table> builder = factory.create(new TestItem());

    assertNotNull(builder);

    Table table = Mockito.mock(Table.class);

    TableContent content = null;
    try {
      content = builder.withDescription("test").withData(table).withProperty("key", "value").save();
    } catch (IncompleteException e) {
      fail("The builder should not fail to create this content", e);
    }

    assertNotNull(content);
    assertEquals("test", content.getDescription());
    assertEquals(table, content.getData());
    assertTrue(content.getProperties().has("key"));
    assertEquals("value", content.getProperties().get("key").get());
  }
}
