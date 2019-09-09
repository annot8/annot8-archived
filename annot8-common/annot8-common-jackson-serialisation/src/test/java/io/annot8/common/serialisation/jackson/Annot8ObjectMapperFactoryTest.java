/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Annot8ObjectMapperFactoryTest {

  @Test
  public void testGetObjectMapper() {

    FileSerializer serializer = new FileSerializer();
    FileDeserializer deserializer = new FileDeserializer();

    Annot8ObjectMapperFactory factory =
        new Annot8ObjectMapperFactory(
            Collections.singletonList(serializer), Collections.singletonList(deserializer));

    ObjectMapper objectMapper = factory.configure(new ObjectMapper());

    Assertions.assertNotNull(objectMapper);

    String fileString = null;
    try {
      fileString = objectMapper.writeValueAsString(new File("tesitng"));
    } catch (JsonProcessingException e) {
      Assertions.fail("Unexpected exception, this object mapper should handle File objects", e);
    }

    File file = null;
    try {
      file = objectMapper.readValue(fileString, File.class);
    } catch (IOException e) {
      Assertions.fail(
          "Unexpected exception, this object mapper should handle File deserialization", e);
    }

    Assertions.assertNotNull(file);
  }

  @Test
  public void testNullGetObjectMapper() {
    Annot8ObjectMapperFactory factory = new Annot8ObjectMapperFactory(null, null);
    Assertions.assertNotNull(factory.configure(new ObjectMapper()));
  }

  @Test
  public void testEmptyListObjectMapper() {
    Annot8ObjectMapperFactory factory =
        new Annot8ObjectMapperFactory(Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    Assertions.assertNotNull(factory.configure(new ObjectMapper()));
  }
}
