/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.annot8.common.data.bounds.SpanBounds;

public class SpanBoundsSerializerTest {

  @Test
  public void testSerialize() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    SpanBoundsSerializer serializer = new SpanBoundsSerializer();
    module.addSerializer(SpanBounds.class, serializer);
    mapper.registerModule(module);

    SpanBounds bounds = new SpanBounds(0, 10);
    String serialized = null;

    try {
      serialized = mapper.writeValueAsString(bounds);
    } catch (JsonProcessingException e) {
      fail("Serialization should not fail", e);
    }

    String expectedJson = "{\"begin\":0,\"end\":10}";

    assertEquals(expectedJson, serialized);
  }
}
