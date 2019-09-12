package io.annot8.common.serialization;

import io.annot8.core.components.Annot8ComponentDescriptor;

import java.io.IOException;

public interface Serializer<T> {
  Annot8ComponentDescriptor deserialize(T serialized) throws IOException, ClassNotFoundException;
  T serialize(Annot8ComponentDescriptor descriptor) throws IOException;
}
