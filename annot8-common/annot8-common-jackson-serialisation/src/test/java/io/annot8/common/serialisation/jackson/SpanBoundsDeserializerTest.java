/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.annot8.common.data.bounds.SpanBounds;

public class SpanBoundsDeserializerTest {

  @Test
  public void testDeserialize() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    SpanBoundsDeserializer deserializer = new SpanBoundsDeserializer();
    module.addDeserializer(SpanBounds.class, deserializer);
    mapper.registerModule(module);

    String bounds = "{\"begin\":10, \"end\":20}";

    SpanBounds spanBounds = null;
    try {
      spanBounds = mapper.readValue(bounds, SpanBounds.class);
    } catch (IOException e) {
      Assertions.fail("Unexpected exception", e);
    }

    Assertions.assertNotNull(spanBounds);
    Assertions.assertEquals(10, spanBounds.getBegin());
    Assertions.assertEquals(20, spanBounds.getEnd());
  }
}
