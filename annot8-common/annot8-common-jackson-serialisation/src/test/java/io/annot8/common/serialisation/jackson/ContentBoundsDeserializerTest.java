/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.annot8.common.data.bounds.ContentBounds;

public class ContentBoundsDeserializerTest {

  @Test
  public void testDeserialize() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule();
    module.addDeserializer(ContentBounds.class, new ContentBoundsDeserializer());
    mapper.registerModule(module);

    ContentBounds bounds = null;
    try {
      bounds = mapper.readValue("{}", ContentBounds.class);
    } catch (IOException e) {
      fail("Test is not expected to fail here", e);
    }

    assertNotNull(bounds);
  }
}
