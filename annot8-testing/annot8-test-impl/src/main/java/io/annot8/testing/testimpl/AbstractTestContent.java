/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.util.UUID;
import java.util.function.Supplier;

import io.annot8.common.implementations.stores.AnnotationStoreFactory;
import io.annot8.core.data.Content;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.stores.AnnotationStore;

public abstract class AbstractTestContent<D> implements Content<D> {

  private String id;
  private final Class<D> dataClass;
  private String name;
  private ImmutableProperties properties;
  private AnnotationStore annotations;

  private Supplier<D> data;

  public AbstractTestContent(Class<D> dataClass) {
    this(dataClass, TestConstants.CONTENT_NAME);
  }

  public AbstractTestContent(Class<D> dataClass, String name) {
    this(dataClass, UUID.randomUUID().toString(), name, new TestProperties());
  }

  public AbstractTestContent(
      Class<D> dataClass, String id, String name, ImmutableProperties properties) {
    this(dataClass, id, name, properties, (D) null);
  }

  public AbstractTestContent(
      Class<D> dataClass,
      String id,
      String name,
      ImmutableProperties properties,
      Supplier<D> data) {
    this(dataClass, n -> new TestAnnotationStore(n.getId()), id, name, properties, data);
  }

  public AbstractTestContent(
      Class<D> dataClass,
      AnnotationStoreFactory annotationStoreFactory,
      String id,
      String name,
      ImmutableProperties properties,
      Supplier<D> data) {
    this.id = id;
    this.dataClass = dataClass;
    this.name = name;
    this.properties = properties;
    this.data = data;

    this.annotations = annotationStoreFactory.create(this);
  }

  public AbstractTestContent(
      Class<D> dataClass, String id, String name, ImmutableProperties properties, D data) {
    this(dataClass, id, name, properties, () -> data);
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
  }

  public Class<D> getDataClass() {
    return dataClass;
  }

  @Override
  public D getData() {
    return data.get();
  }

  public void setData(D data) {
    if (data == null) {
      this.setData((Supplier<D>) null);
    } else {
      this.setData(() -> data);
    }
  }

  public void setData(Supplier<D> data) {
    this.data = data;
  }

  @Override
  public AnnotationStore getAnnotations() {
    return annotations;
  }

  public void setAnnotations(AnnotationStore annotations) {
    this.annotations = annotations;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public ImmutableProperties getProperties() {
    return properties;
  }

  public void setProperties(ImmutableProperties properties) {
    this.properties = properties;
  }
}
