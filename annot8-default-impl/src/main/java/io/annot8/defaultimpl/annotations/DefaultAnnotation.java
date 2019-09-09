/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.annotations;

import java.util.Optional;
import java.util.UUID;

import io.annot8.common.implementations.annotations.AbstractAnnotation;
import io.annot8.common.implementations.properties.MapImmutableProperties;
import io.annot8.common.implementations.properties.MapMutableProperties;
import io.annot8.common.utils.properties.EmptyImmutableProperties;
import io.annot8.core.annotations.Annotation;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.properties.MutableProperties;
import io.annot8.core.properties.Properties;

/** Simple implementation of Annotation interface */
public class DefaultAnnotation extends AbstractAnnotation {

  private final String id;
  private final String type;
  private final ImmutableProperties properties;
  private final Bounds bounds;
  private final String content;

  private DefaultAnnotation(
      final String id,
      final String type,
      final ImmutableProperties properties,
      final Bounds bounds,
      final String content) {
    this.id = id;
    this.type = type;
    this.properties = properties;
    this.bounds = bounds;
    this.content = content;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getType() {
    return type;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

  @Override
  public Bounds getBounds() {
    return bounds;
  }

  @Override
  public String getContentId() {
    return content;
  }

  /**
   * AbstractContentBuilder class for DefaultAnnotation, using UUID for generating new IDs and
   * InMemoryImmutableProperties or EmptyImmutableProperties for the properties.
   */
  public static class Builder implements Annotation.Builder {

    private final String content;
    private String type = null;
    private MutableProperties properties = new MapMutableProperties();
    private Bounds bounds = null;
    private String id = null;

    public Builder(String content) {
      this.content = content;
    }

    @Override
    public Annotation.Builder withId(String id) {
      this.id = id;
      return this;
    }

    @Override
    public io.annot8.core.annotations.Annotation.Builder withType(String type) {
      this.type = type;
      return this;
    }

    @Override
    public io.annot8.core.annotations.Annotation.Builder withProperty(String key, Object value) {
      properties.set(key, value);
      return this;
    }

    @Override
    public Annotation.Builder withPropertyIfPresent(String key, Optional<?> value) {
      value.ifPresent(o -> properties.set(key, o));
      return this;
    }

    @Override
    public Annotation.Builder withoutProperty(String key, Object value) {
      Optional<Object> val = properties.get(key);
      if (val.isPresent() && val.get().equals(value)) {
        properties.remove(key);
      }

      return this;
    }

    @Override
    public Annotation.Builder withoutProperty(String key) {
      properties.remove(key);
      return this;
    }

    @Override
    public io.annot8.core.annotations.Annotation.Builder withProperties(Properties properties) {
      this.properties = new MapMutableProperties(properties);
      return this;
    }

    @Override
    public io.annot8.core.annotations.Annotation.Builder newId() {
      this.id = null;
      return this;
    }

    @Override
    public io.annot8.core.annotations.Annotation.Builder from(Annotation from) {
      this.id = from.getId();
      this.type = from.getType();
      this.properties = new MapMutableProperties(from.getProperties());
      this.bounds = from.getBounds();

      return this;
    }

    @Override
    public Annotation save() throws IncompleteException {
      if (id == null) {
        id = UUID.randomUUID().toString();
      }

      if (type == null) {
        throw new IncompleteException("Type has not been set");
      }

      if (bounds == null) {
        throw new IncompleteException("Bounds has not been set");
      }

      if (content == null) {
        throw new IncompleteException("Content name has not been set");
      }

      ImmutableProperties immutableProperties;
      if (properties.getAll().isEmpty()) {
        immutableProperties = EmptyImmutableProperties.getInstance();
      } else {
        immutableProperties = new MapImmutableProperties.Builder().from(properties).save();
      }

      return new DefaultAnnotation(id, type, immutableProperties, bounds, content);
    }

    @Override
    public io.annot8.core.annotations.Annotation.Builder withBounds(Bounds bounds) {
      this.bounds = bounds;
      return this;
    }
  }
}
