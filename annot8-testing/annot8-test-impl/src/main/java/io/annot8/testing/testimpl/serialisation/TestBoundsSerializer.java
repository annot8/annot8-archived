/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.serialisation;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import io.annot8.common.serialisation.jackson.AbstractAnnot8Serializer;
import io.annot8.testing.testimpl.TestBounds;

public class TestBoundsSerializer extends AbstractAnnot8Serializer<TestBounds> {

  public TestBoundsSerializer() {
    super(TestBounds.class);
  }

  @Override
  public void serialize(TestBounds value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {

    // We write something just so we've written something we can check
    gen.writeStartObject();
    gen.writeStringField(TestBoundsDeserializer.KEY, value.getId());
    gen.writeEndObject();
  }
}
