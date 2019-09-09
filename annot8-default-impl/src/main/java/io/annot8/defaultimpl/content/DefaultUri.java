/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.net.URI;
import java.util.function.Supplier;

import io.annot8.common.data.content.UriContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultUri implements UriContent {

  private final String id;
  private final String name;
  private final URI url;
  private final ImmutableProperties properties;
  private final AnnotationStore store;

  private DefaultUri(String id, String name, URI url, ImmutableProperties properties) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.properties = properties;
    this.store = new DefaultAnnotationStore(id);
  }

  @Override
  public URI getData() {
    return url;
  }

  @Override
  public Class<URI> getDataClass() {
    return URI.class;
  }

  @Override
  public Class<? extends Content<URI>> getContentClass() {
    return UriContent.class;
  }

  @Override
  public AnnotationStore getAnnotations() {
    return store;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

  public static class Builder extends AbstractContentBuilder<URI, UriContent> {

    @Override
    protected UriContent create(
        String id, String name, ImmutableProperties properties, Supplier<URI> data) {
      return new DefaultUri(id, name, data.get(), properties);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<URI, UriContent> {

    public BuilderFactory() {
      super(URI.class, UriContent.class);
    }

    @Override
    public Content.Builder<UriContent, URI> create(BaseItem item) {
      return new Builder();
    }
  }
}
