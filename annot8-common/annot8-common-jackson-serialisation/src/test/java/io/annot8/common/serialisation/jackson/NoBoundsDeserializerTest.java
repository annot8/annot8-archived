/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.annot8.common.data.bounds.NoBounds;

public class NoBoundsDeserializerTest {

  @Test
  public void testDeserialize() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(NoBounds.class, new NoBoundsDeserializer());
    mapper.registerModule(module);

    NoBounds bounds = null;
    try {
      bounds = mapper.readValue("{}", NoBounds.class);
    } catch (IOException e) {
      fail("Test is not expected to fail here", e);
    }

    assertNotNull(bounds);
  }
}
