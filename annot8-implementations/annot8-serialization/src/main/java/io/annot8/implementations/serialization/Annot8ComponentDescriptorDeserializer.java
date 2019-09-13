/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.serialization;

import java.lang.reflect.Type;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.api.components.Annot8ComponentDescriptor;

public class Annot8ComponentDescriptorDeserializer
    implements JsonbDeserializer<Annot8ComponentDescriptor> {

  private static final Jsonb jb = JsonbBuilder.create();
  private static final Logger LOGGER =
      LoggerFactory.getLogger(Annot8ComponentDescriptorDeserializer.class);

  @Override
  public Annot8ComponentDescriptor deserialize(
      JsonParser parser, DeserializationContext ctx, Type type) {

    Annot8ComponentDescriptor desc = null;
    while (parser.hasNext()) {
      JsonParser.Event event = parser.next();
      if (event == JsonParser.Event.KEY_NAME) {
        String className = parser.getString();
        parser.next();
        try {
          // TODO: This is not a good way of doing it, as we lose any user provided config
          // (including additional deserializers)
          //    However, if we don't do this we get a recursive error because we try to deserialize
          // with ctx which itself tries to use this deserializer
          //    To change this, we need Yasson or JSON-B to update how they implement things
          //    There are a number of open GitHub tickets about this
          //        https://github.com/eclipse-ee4j/yasson/issues/133
          //        https://github.com/eclipse-ee4j/yasson/issues/279
          //        https://github.com/eclipse-ee4j/jsonb-api/issues/147
          desc =
              jb.fromJson(
                  parser.getObject().toString(),
                  Class.forName(className).asSubclass(Annot8ComponentDescriptor.class));
          // desc =
          // ctx.deserialize(Class.forName(className).asSubclass(Annot8ComponentDescriptor.class),
          // parser);
        } catch (ClassNotFoundException e) {
          LOGGER.error("Deserialization failed - could not find class " + className, e);
        }
      }
    }
    return desc;
  }
}
