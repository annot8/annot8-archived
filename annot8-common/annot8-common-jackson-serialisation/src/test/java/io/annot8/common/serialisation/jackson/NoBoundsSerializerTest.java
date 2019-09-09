/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.annot8.common.data.bounds.NoBounds;

public class NoBoundsSerializerTest {

  @Test
  public void testSerialize() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addSerializer(new NoBoundsSerializer());
    mapper.registerModule(module);

    String json = null;
    try {
      json = mapper.writeValueAsString(NoBounds.getInstance());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    assertNotNull(json);
    assertEquals("{}", json);
  }
}
