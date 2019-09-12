/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.serialization;

import java.io.IOException;

import io.annot8.core.components.Annot8ComponentDescriptor;

public interface Serializer<T> {
  Annot8ComponentDescriptor deserialize(T serialized) throws IOException, ClassNotFoundException;

  T serialize(Annot8ComponentDescriptor descriptor) throws IOException;
}
