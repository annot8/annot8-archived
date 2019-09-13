/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.defaultimpl.content;

import java.util.function.Supplier;

import io.annot8.common.data.content.Text;
import io.annot8.common.implementations.content.AbstractContent;
import io.annot8.common.implementations.content.AbstractContentBuilder;
import io.annot8.common.implementations.content.AbstractContentBuilderFactory;
import io.annot8.api.data.Content;
import io.annot8.api.data.Item;
import io.annot8.api.properties.ImmutableProperties;
import io.annot8.defaultimpl.stores.DefaultAnnotationStore;

public class DefaultText extends AbstractContent<String> implements Text {

  private DefaultText(
      Item item,
      String id,
      String description,
      ImmutableProperties properties,
      Supplier<String> data) {
    super(
        item,
        String.class,
        Text.class,
        DefaultAnnotationStore::new,
        id,
        description,
        properties,
        data);
  }

  public static class Builder extends AbstractContentBuilder<String, DefaultText> {

    public Builder(Item item) {
      super(item);
    }

    @Override
    public DefaultText create(
        String id, String description, ImmutableProperties properties, Supplier<String> data) {
      return new DefaultText(getItem(), id, description, properties, data);
    }
  }

  public static class BuilderFactory extends AbstractContentBuilderFactory<String, DefaultText> {

    public BuilderFactory() {
      super(String.class, DefaultText.class);
    }

    @Override
    public Content.Builder<DefaultText, String> create(Item item) {
      return new DefaultText.Builder(item);
    }
  }
}