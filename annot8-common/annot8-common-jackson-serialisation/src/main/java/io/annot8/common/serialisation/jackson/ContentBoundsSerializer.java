/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialisation.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.annot8.common.data.bounds.ContentBounds;

public class ContentBoundsSerializer extends AbstractAnnot8Serializer<ContentBounds> {

  public ContentBoundsSerializer() {
    super(ContentBounds.class);
  }

  @Override
  public void serialize(ContentBounds value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeEndObject();
  }
}
