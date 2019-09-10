/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.content;

import java.util.function.Supplier;

import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;

public abstract class AbstractContent<D> implements Content<D> {

  private final Class<D> dataClass;
  private final Class<? extends Content<D>> contentClass;

  private final String id;
  private final String description;
  private final AnnotationStore annotations;
  private final ImmutableProperties properties;
  private final Supplier<D> data;

  protected AbstractContent(
      Class<D> dataClass,
      Class<? extends Content<D>> contentClass,
      AnnotationStore annotations,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<D> data) {
    this.dataClass = dataClass;
    this.contentClass = contentClass;
    this.annotations = annotations;
    this.id = id;
    this.description = description;
    this.properties = properties;
    this.data = data;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public D getData() {
    return data.get();
  }

  @Override
  public Class<D> getDataClass() {
    return dataClass;
  }

  @Override
  public Class<? extends Content<D>> getContentClass() {
    return contentClass;
  }

  @Override
  public AnnotationStore getAnnotations() {
    return annotations;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }
}
