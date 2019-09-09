/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.content;

import java.net.URI;
import java.util.function.Supplier;

import io.annot8.common.data.content.UriContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.testing.testimpl.TestAnnotationStore;

public class TestUriContent implements UriContent {

  private URI data;
  private String id;
  private String name;
  private ImmutableProperties properties;
  private AnnotationStore store;

  public TestUriContent(String id, String name, ImmutableProperties properties, URI data) {
    this.id = id;
    this.name = name;
    this.properties = properties;
    this.store = new TestAnnotationStore();
    this.data = data;
  }

  @Override
  public URI getData() {
    return data;
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

  public static class TestUriBuilder extends AbstractContentBuilder<URI, UriContent> {

    @Override
    protected UriContent create(
        String id, String name, ImmutableProperties properties, Supplier<URI> data) {
      return new TestUriContent(id, name, properties, data.get());
    }
  }

  public static class TestURLBuilderFactory extends AbstractContentBuilderFactory<URI, UriContent> {

    public TestURLBuilderFactory() {
      super(URI.class, UriContent.class);
    }

    @Override
    public Builder<UriContent, URI> create(BaseItem item) {
      return new TestUriBuilder();
    }
  }
}
