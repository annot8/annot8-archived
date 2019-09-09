/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;

import io.annot8.common.data.bounds.ContentBounds;

public class ContentBoundsDeserializer extends AbstractAnnot8Deserializer<ContentBounds> {

  public ContentBoundsDeserializer() {
    super(ContentBounds.class);
  }

  @Override
  public ContentBounds deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    return ContentBounds.getInstance();
  }
}
