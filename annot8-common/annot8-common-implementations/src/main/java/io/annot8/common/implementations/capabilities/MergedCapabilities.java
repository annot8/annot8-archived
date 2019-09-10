/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import io.annot8.core.capabilities.AnnotationCapability;
import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.ContentCapability;
import io.annot8.core.capabilities.GroupCapability;
import io.annot8.core.capabilities.ResourceCapability;

/** Combines multiple capabilities into a single Capabilities object. */
public class MergedCapabilities implements Capabilities {

  private final Capabilities[] capabilities;

  /**
   * New instance
   *
   * @param capabilities the capabiltiies to combine (may be zero or more)
   */
  public MergedCapabilities(Capabilities... capabilities) {
    this.capabilities = capabilities;
  }

  @Override
  public Stream<AnnotationCapability> getProcessedAnnotations() {
    return merge(Capabilities::getProcessedAnnotations);
  }

  @Override
  public Stream<AnnotationCapability> getCreatedAnnotations() {
    return merge(Capabilities::getCreatedAnnotations);
  }

  @Override
  public Stream<AnnotationCapability> getDeletedAnnotations() {
    return merge(Capabilities::getDeletedAnnotations);
  }

  @Override
  public Stream<GroupCapability> getProcessedGroups() {
    return merge(Capabilities::getProcessedGroups);
  }

  @Override
  public Stream<GroupCapability> getCreatedGroups() {
    return merge(Capabilities::getCreatedGroups);
  }

  @Override
  public Stream<GroupCapability> getDeletedGroups() {
    return merge(Capabilities::getDeletedGroups);
  }

  @Override
  public Stream<ContentCapability> getCreatedContent() {
    return merge(Capabilities::getCreatedContent);
  }

  @Override
  public Stream<ContentCapability> getDeletedContent() {
    return merge(Capabilities::getDeletedContent);
  }

  @Override
  public Stream<ContentCapability> getProcessedContent() {
    return merge(Capabilities::getProcessedContent);
  }

  @Override
  public Stream<ResourceCapability> getUsedResources() {
    return merge(Capabilities::getUsedResources);
  }

  private <T> Stream<T> merge(Function<Capabilities, Stream<T>> extractor) {
    if (capabilities == null || capabilities.length == 0) {
      return Stream.empty();
    }

    return Arrays.stream(capabilities)
        .filter(Objects::nonNull)
        .flatMap(extractor)
        .filter(Objects::nonNull)
        .distinct();
  }
}
