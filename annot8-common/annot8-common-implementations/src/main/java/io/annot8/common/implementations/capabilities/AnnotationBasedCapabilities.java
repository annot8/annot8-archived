/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import io.annot8.core.capabilities.AnnotationCapability;
import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.ContentCapability;
import io.annot8.core.capabilities.CreatesAnnotation;
import io.annot8.core.capabilities.CreatesContent;
import io.annot8.core.capabilities.CreatesGroup;
import io.annot8.core.capabilities.DeletesAnnotation;
import io.annot8.core.capabilities.DeletesContent;
import io.annot8.core.capabilities.DeletesGroup;
import io.annot8.core.capabilities.GroupCapability;
import io.annot8.core.capabilities.ProcessesAnnotation;
import io.annot8.core.capabilities.ProcessesContent;
import io.annot8.core.capabilities.ProcessesGroup;
import io.annot8.core.capabilities.ResourceCapability;
import io.annot8.core.capabilities.UsesResource;
import io.annot8.core.components.Annot8Component;

/**
 * Implementation of Capabilities which uses annotations on the component class to determine its'
 * capabilities.
 *
 * <p>Annotations are defined in the same package.
 *
 * <p>Ideally this implementation would not be part of core, but it acts as the default
 * implementation and as such needs to be importable by {@link Annot8Component}.
 */
public class AnnotationBasedCapabilities implements Capabilities {

  private final Class<?> clazz;

  /** Constructor which will review the annotations on the provided class to implement interface. */
  public AnnotationBasedCapabilities(Class<?> clazz) {
    this.clazz = clazz;
  }

  @Override
  public Stream<AnnotationCapability> getCreatedAnnotations() {
    return extractFromAnnotations(CreatesAnnotation.class, AnnotationCapability::new);
  }

  @Override
  public Stream<AnnotationCapability> getProcessedAnnotations() {
    return extractFromAnnotations(ProcessesAnnotation.class, AnnotationCapability::new);
  }

  @Override
  public Stream<AnnotationCapability> getDeletedAnnotations() {
    return extractFromAnnotations(DeletesAnnotation.class, AnnotationCapability::new);
  }

  @Override
  public Stream<GroupCapability> getProcessedGroups() {
    return extractFromAnnotations(ProcessesGroup.class, GroupCapability::new);
  }

  @Override
  public Stream<GroupCapability> getCreatedGroups() {
    return extractFromAnnotations(CreatesGroup.class, GroupCapability::new);
  }

  @Override
  public Stream<GroupCapability> getDeletedGroups() {
    return extractFromAnnotations(DeletesGroup.class, GroupCapability::new);
  }

  @Override
  public Stream<ContentCapability> getCreatedContent() {
    return extractFromAnnotations(CreatesContent.class, ContentCapability::new);
  }

  @Override
  public Stream<ContentCapability> getDeletedContent() {
    return extractFromAnnotations(DeletesContent.class, ContentCapability::new);
  }

  @Override
  public Stream<ContentCapability> getProcessedContent() {
    return extractFromAnnotations(ProcessesContent.class, ContentCapability::new);
  }

  @Override
  public Stream<ResourceCapability> getUsedResources() {
    return extractFromAnnotations(UsesResource.class, ResourceCapability::new);
  }

  protected <A extends Annotation, T> Stream<T> extractFromAnnotations(
      Class<A> annotationClass, Function<A, T> extractor) {
    A[] annotations = clazz.getAnnotationsByType(annotationClass);
    return Arrays.stream(annotations).map(extractor).distinct();
  }
}
