/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.content;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import io.annot8.common.implementations.properties.MapImmutableProperties;
import io.annot8.core.data.Content;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.properties.Properties;

public abstract class AbstractContentBuilder<D, C extends Content<D>>
    implements Content.Builder<C, D> {

  private final ImmutableProperties.Builder properties = new MapImmutableProperties.Builder();
  private String name;
  private String id;

  private Supplier<D> data;

  @Override
  public Content.Builder<C, D> withId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public Content.Builder<C, D> withName(String name) {
    this.name = name;
    return this;
  }

  @Override
  public Content.Builder<C, D> withData(Supplier<D> data) {
    this.data = data;
    return this;
  }

  @Override
  public Content.Builder<C, D> from(C from) {
    return this;
  }

  @Override
  public Content.Builder<C, D> withProperty(String key, Object value) {
    properties.withProperty(key, value);
    return this;
  }

  @Override
  public Content.Builder<C, D> withPropertyIfPresent(String key, Optional<?> value) {
    value.ifPresent(o -> properties.withProperty(key, o));
    return this;
  }

  @Override
  public Content.Builder<C, D> withProperties(Properties properties) {
    this.properties.withProperties(properties);
    return this;
  }

  @Override
  public Content.Builder<C, D> withoutProperty(String key, Object value) {
    properties.withoutProperty(key, value);
    return this;
  }

  @Override
  public Content.Builder<C, D> withoutProperty(String key) {
    properties.withoutProperty(key);
    return this;
  }

  @Override
  public C save() throws IncompleteException {
    if (id == null) {
      id = UUID.randomUUID().toString();
    }

    if (name == null) {
      throw new IncompleteException("Name is required");
    }

    if (data == null) {
      throw new IncompleteException("Data supplier is required");
    }

    return create(id, name, properties.save(), data);
  }

  protected abstract C create(
      String id, String name, ImmutableProperties properties, Supplier<D> data);
}
