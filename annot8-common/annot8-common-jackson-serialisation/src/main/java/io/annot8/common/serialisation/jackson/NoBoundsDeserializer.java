/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

import io.annot8.common.data.bounds.NoBounds;

public class NoBoundsDeserializer extends AbstractAnnot8Deserializer<NoBounds> {

  public NoBoundsDeserializer() {
    super(NoBounds.class);
  }

  @Override
  public NoBounds deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    return NoBounds.getInstance();
  }
}
