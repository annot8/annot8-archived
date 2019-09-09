/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.content;

import io.annot8.common.implementations.factories.ContentBuilderFactory;
import io.annot8.core.data.Content;

public abstract class AbstractContentBuilderFactory<D, C extends Content<D>>
    implements ContentBuilderFactory<D, C> {

  private final Class<D> dataClass;
  private final Class<C> contentClass;

  protected AbstractContentBuilderFactory(Class<D> dataClass, Class<C> contentClass) {
    this.dataClass = dataClass;
    this.contentClass = contentClass;
  }

  @Override
  public Class<D> getDataClass() {
    return dataClass;
  }

  @Override
  public Class<C> getContentClass() {
    return contentClass;
  }
}
