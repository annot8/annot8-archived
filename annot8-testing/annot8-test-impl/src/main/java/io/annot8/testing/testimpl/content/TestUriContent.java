/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl.content;

import io.annot8.common.data.content.UriContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.core.data.Content;
import io.annot8.core.data.Item;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;
import io.annot8.testing.testimpl.TestAnnotationStore;

import java.net.URI;
import java.util.function.Supplier;

public class TestUriContent implements UriContent {

  private URI data;
  private Item item;
  private String id;
  private String description;
  private ImmutableProperties properties;
  private AnnotationStore store;

  public TestUriContent(Item item, String id, String description, ImmutableProperties properties, URI data) {
    this.item = item;
    this.id = id;
    this.description = description;
    this.properties = properties;
    this.store = new TestAnnotationStore(this);
    this.data = data;
  }

  @Override
  public Item getItem() {
    return item;
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
  public String getId() {
    return id;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

  public static class TestUriBuilder extends AbstractContentBuilder<URI, UriContent> {

    public TestUriBuilder(Item item) {
      super(item);
    }

    @Override
    protected UriContent create(
        String id, String description, ImmutableProperties properties, Supplier<URI> data) {
      return new TestUriContent(getItem(), id, description, properties, data.get());
    }
  }

  public static class TestURLBuilderFactory extends AbstractContentBuilderFactory<URI, UriContent> {

    public TestURLBuilderFactory() {
      super(URI.class, UriContent.class);
    }

    @Override
    public Builder<UriContent, URI> create(Item item) {
      return new TestUriBuilder(item);
    }
  }
}
