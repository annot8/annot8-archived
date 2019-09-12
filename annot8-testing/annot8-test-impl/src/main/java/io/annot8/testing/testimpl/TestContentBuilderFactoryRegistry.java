/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.data.content.*;
import io.annot8.common.implementations.factories.ContentBuilderFactory;
import io.annot8.common.implementations.registries.SimpleContentBuilderFactoryRegistry;
import io.annot8.core.data.Content;
import io.annot8.core.data.Content.Builder;
import io.annot8.core.data.Item;
import io.annot8.core.properties.Properties;
import io.annot8.testing.testimpl.content.TestFileContent;
import io.annot8.testing.testimpl.content.TestInputStreamContent;
import io.annot8.testing.testimpl.content.TestStringContent;
import io.annot8.testing.testimpl.content.TestTableContent;
import io.annot8.testing.testimpl.content.TestUriContent.TestURLBuilderFactory;

public class TestContentBuilderFactoryRegistry extends SimpleContentBuilderFactoryRegistry {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(TestContentBuilderFactoryRegistry.class);

  public TestContentBuilderFactoryRegistry() {
    this(true);
  }

  public TestContentBuilderFactoryRegistry(boolean includeDefaultContentBuilders) {

    if (includeDefaultContentBuilders) {
      register(Text.class, new TestStringContent.BuilderFactory());
      register(FileContent.class, new TestFileContent.BuilderFactory());
      register(InputStreamContent.class, new TestInputStreamContent.BuilderFactory());
      register(UriContent.class, new TestURLBuilderFactory());
      register(TableContent.class, new TestTableContent.BuilderFactory());
    }
  }

  @Override
  public <D, C extends Content<D>> Optional<ContentBuilderFactory<D, C>> get(
      Class<C> contentClass) {
    // if you specifically ask for testcontent types we get createContent them (without needing
    // registration)
    if (AbstractTestContent.class.isAssignableFrom(contentClass)) {
      try {
        return Optional.of(new TestContentBuilderFactory(contentClass));
      } catch (Exception e) {
        LOGGER.error("Unable to createContent test content", e);
      }
    }

    // Otherwise fallback to standard registry
    return super.get(contentClass);
  }

  public static class TestContentBuilderFactory<D, C extends AbstractTestContent<D>>
      implements ContentBuilderFactory<D, C> {

    private final C instance;
    private final Class<C> contentClass;

    public TestContentBuilderFactory(Class<C> contentClass)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
      this.contentClass = contentClass;
      instance = contentClass.getConstructor().newInstance();
    }

    @Override
    public Content.Builder<C, D> create(Item item) {
      // Add the right item at the last minute
      instance.setItem(item);
      return new TestContentBuilder(instance);
    }

    @Override
    public Class getDataClass() {
      return instance.getDataClass();
    }

    @Override
    public Class<C> getContentClass() {
      return contentClass;
    }
  }

  public static class TestContentBuilder<D, C extends AbstractTestContent<D>>
      implements Content.Builder<C, D> {

    private final C instance;
    private final TestProperties builderProperties = new TestProperties();

    public TestContentBuilder(C instance) {
      this.instance = instance;
    }

    @Override
    public Builder<C, D> withId(String id) {
      this.instance.setId(id);
      return this;
    }

    @Override
    public Builder<C, D> withDescription(String description) {
      instance.setDescription(description);
      return this;
    }

    @Override
    public Builder<C, D> withData(Supplier<D> data) {
      instance.setData(data);
      return this;
    }

    @Override
    public Builder<C, D> from(C from) {
      withProperties(from.getProperties());
      return this;
    }

    @Override
    public Builder<C, D> withProperty(String key, Object value) {
      builderProperties.set(key, value);
      return this;
    }

    @Override
    public Builder<C, D> withPropertyIfPresent(String key, Optional<?> value) {
      value.ifPresent(o -> builderProperties.set(key, o));
      return this;
    }

    @Override
    public Builder<C, D> withoutProperty(String key, Object value) {
      Optional<Object> opt = builderProperties.get(key);
      if (opt.isPresent() && opt.get().equals(value)) {
        builderProperties.remove(key);
      }

      return this;
    }

    @Override
    public Builder<C, D> withoutProperty(String key) {
      builderProperties.remove(key);

      return this;
    }

    @Override
    public Builder<C, D> withProperties(Properties properties) {
      builderProperties.add(properties.getAll());
      return this;
    }

    @Override
    public C save() {
      instance.setProperties(builderProperties);
      return instance;
    }
  }
}
