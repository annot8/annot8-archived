/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.annot8.common.data.bounds.SpanBounds;

public class SpanBoundsSerializer extends AbstractAnnot8Serializer<SpanBounds> {

  public SpanBoundsSerializer() {
    super(SpanBounds.class);
  }

  @Override
  public void serialize(SpanBounds value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("begin", value.getBegin());
    gen.writeNumberField("end", value.getEnd());
    gen.writeEndObject();
  }
}
