/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import io.annot8.common.data.bounds.SpanBounds;

public class SpanBoundsDeserializer extends AbstractAnnot8Deserializer<SpanBounds> {

  public SpanBoundsDeserializer() {
    super(SpanBounds.class);
  }

  @Override
  public SpanBounds deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p.getCodec().readTree(p);
    int begin = node.get("begin").intValue();
    int end = node.get("end").intValue();
    return new SpanBounds(begin, end);
  }
}
