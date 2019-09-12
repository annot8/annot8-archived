/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.annot8.core.components.Annot8ComponentDescriptor;

public class JsonTest {

  private static final TestDescriptor descriptor = new TestDescriptor("Test", "localhost", 8080);
  private static final String serialized =
      "{\""
          + TestDescriptor.class.getName()
          + "\":{\"name\":\"Test\",\"settings\":{\"host\":\"localhost\",\"port\":8080}}}";

  @Test
  public void testSerialize() {
    String json = new Json().serialize(descriptor);
    System.out.println(json);
    Assertions.assertEquals(serialized, json);
  }

  @Test
  public void testDeserialize() throws Exception {
    Annot8ComponentDescriptor acd = new Json().deserialize(serialized);
    Assertions.assertEquals(TestDescriptor.class, acd.getClass());
    assertEquals(descriptor.getName(), acd.getName());

    TestDescriptor td = (TestDescriptor) acd;
    assertEquals(descriptor.getSettings().getHost(), td.getSettings().getHost());
    assertEquals(descriptor.getSettings().getPort(), td.getSettings().getPort());
  }
}
