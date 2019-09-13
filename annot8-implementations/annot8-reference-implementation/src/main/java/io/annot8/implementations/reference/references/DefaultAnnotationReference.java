/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.implementations.reference.references;

import java.util.Optional;

import io.annot8.api.annotations.Annotation;
import io.annot8.api.data.Content;
import io.annot8.api.data.Item;
import io.annot8.implementations.support.references.AbstractAnnotationReference;

/**
 * A reference which will always retrieve the latest annotation from the appropriate annotation
 * store.
 *
 * <p>Does not hold a reference to the group.
 */
public class DefaultAnnotationReference extends AbstractAnnotationReference {

  private final Item item;

  private final String contentId;

  private final String annotationId;

  /** New reference either from another reference or manually created. */
  public DefaultAnnotationReference(Item item, String contentId, String annotationId) {
    this.item = item;
    this.contentId = contentId;
    this.annotationId = annotationId;
  }

  /** Create an annotation reference for the annotation. */
  public static DefaultAnnotationReference to(Item item, Annotation annotation) {
    return new DefaultAnnotationReference(item, annotation.getContentId(), annotation.getId());
  }

  @Override
  public String getAnnotationId() {
    return annotationId;
  }

  @Override
  public String getContentId() {
    return contentId;
  }

  @Override
  public Optional<Annotation> toAnnotation() {
    return item.getContent(contentId)
        .map(Content::getAnnotations)
        .flatMap(store -> store.getById(annotationId));
  }
}
