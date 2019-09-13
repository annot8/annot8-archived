/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import org.junit.jupiter.api.Test;

import io.annot8.api.components.Annot8ComponentDescriptor;

public class Annot8ComponentDescriptorDeserializerTest {
  private static final TestDescriptor descriptor = new TestDescriptor("Test", "localhost", 8080);
  private static final String serialized =
      "{\""
          + TestDescriptor.class.getName()
          + "\":{\"name\":\"Test\",\"settings\":{\"host\":\"localhost\",\"port\":8080}}}";

  @Test
  public void test() {
    JsonbConfig config =
        new JsonbConfig().withDeserializers(new Annot8ComponentDescriptorDeserializer());
    Jsonb jb = JsonbBuilder.create(config);

    Annot8ComponentDescriptor desc = jb.fromJson(serialized, Annot8ComponentDescriptor.class);
    assertEquals(TestDescriptor.class, desc.getClass());
    assertEquals("Test", desc.getName());

    assertEquals(TestSettings.class, desc.getSettings().getClass());
    TestSettings ts = (TestSettings) desc.getSettings();
    assertEquals("localhost", ts.getHost());
    assertEquals(8080, ts.getPort());

    assertEquals(TestProcessor.class, desc.create(null).getClass());
  }
}
