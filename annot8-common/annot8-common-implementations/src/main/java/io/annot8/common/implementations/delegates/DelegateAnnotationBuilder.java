/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.delegates;

import java.util.Optional;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.annotations.Annotation.Builder;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.Properties;

public class DelegateAnnotationBuilder implements Annotation.Builder {

  private final Builder delegate;

  public DelegateAnnotationBuilder(Annotation.Builder delegate) {
    this.delegate = delegate;
  }

  @Override
  public Builder withBounds(Bounds bounds) {
    delegate.withBounds(bounds);
    return this;
  }

  @Override
  public Builder from(Annotation from) {
    delegate.from(from);
    return this;
  }

  @Override
  public Builder withId(String id) {
    delegate.withId(id);
    return this;
  }

  @Override
  public Builder newId() {
    delegate.newId();
    return this;
  }

  @Override
  public Builder withProperty(String key, Object value) {
    delegate.withProperty(key, value);
    return this;
  }

  @Override
  public Builder withPropertyIfPresent(String key, Optional<?> value) {
    value.ifPresent(o -> delegate.withProperty(key, o));
    return this;
  }

  @Override
  public Builder withoutProperty(String key, Object value) {
    delegate.withoutProperty(key, value);
    return this;
  }

  @Override
  public Builder withoutProperty(String key) {
    delegate.withoutProperty(key);
    return this;
  }

  @Override
  public Builder withProperties(Properties properties) {
    delegate.withProperties(properties);
    return this;
  }

  @Override
  public Annotation save() throws IncompleteException {
    return delegate.save();
  }

  @Override
  public Builder withType(String type) {
    delegate.withType(type);
    return this;
  }
}
