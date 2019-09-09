/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import io.annot8.core.capabilities.AnnotationCapability;
import io.annot8.core.capabilities.Capabilities;
import io.annot8.core.capabilities.ContentCapability;
import io.annot8.core.capabilities.GroupCapability;
import io.annot8.core.capabilities.ResourceCapability;

public class SetCapabilities implements Capabilities {

  private final Set<AnnotationCapability> processedAnnotations;
  private final Set<AnnotationCapability> createdAnnotations;
  private final Set<AnnotationCapability> deletedAnnotations;

  private final Set<GroupCapability> processedGroups;
  private final Set<GroupCapability> createdGroups;
  private final Set<GroupCapability> deletedGroups;

  private final Set<ContentCapability> processedContent;
  private final Set<ContentCapability> createdContent;
  private final Set<ContentCapability> deletedContent;

  private final Set<ResourceCapability> usedResources;

  public SetCapabilities(
      Set<AnnotationCapability> processedAnnotations,
      Set<AnnotationCapability> createdAnnotations,
      Set<AnnotationCapability> deletedAnnotations,
      Set<GroupCapability> processedGroups,
      Set<GroupCapability> createdGroups,
      Set<GroupCapability> deletedGroups,
      Set<ContentCapability> processedContent,
      Set<ContentCapability> createdContent,
      Set<ContentCapability> deletedContent,
      Set<ResourceCapability> usedResources) {

    this.processedAnnotations = processedAnnotations;
    this.createdAnnotations = createdAnnotations;
    this.deletedAnnotations = deletedAnnotations;
    this.processedGroups = processedGroups;
    this.createdGroups = createdGroups;
    this.deletedGroups = deletedGroups;
    this.processedContent = processedContent;
    this.createdContent = createdContent;
    this.deletedContent = deletedContent;
    this.usedResources = usedResources;
  }

  @Override
  public Stream<AnnotationCapability> getProcessedAnnotations() {
    return processedAnnotations.stream();
  }

  @Override
  public Stream<AnnotationCapability> getCreatedAnnotations() {
    return createdAnnotations.stream();
  }

  @Override
  public Stream<AnnotationCapability> getDeletedAnnotations() {
    return deletedAnnotations.stream();
  }

  @Override
  public Stream<GroupCapability> getProcessedGroups() {
    return processedGroups.stream();
  }

  @Override
  public Stream<GroupCapability> getCreatedGroups() {
    return createdGroups.stream();
  }

  @Override
  public Stream<GroupCapability> getDeletedGroups() {
    return deletedGroups.stream();
  }

  @Override
  public Stream<ContentCapability> getCreatedContent() {
    return createdContent.stream();
  }

  @Override
  public Stream<ContentCapability> getDeletedContent() {
    return deletedContent.stream();
  }

  @Override
  public Stream<ContentCapability> getProcessedContent() {
    return processedContent.stream();
  }

  @Override
  public Stream<ResourceCapability> getUsedResources() {
    return usedResources.stream();
  }

  public static class Builder implements Capabilities.Builder {

    private final Set<AnnotationCapability> processedAnnotations = new HashSet<>();
    private final Set<AnnotationCapability> createdAnnotations = new HashSet<>();
    private final Set<AnnotationCapability> deletedAnnotations = new HashSet<>();

    private final Set<GroupCapability> processedGroups = new HashSet<>();
    private final Set<GroupCapability> createdGroups = new HashSet<>();
    private final Set<GroupCapability> deletedGroups = new HashSet<>();

    private final Set<ContentCapability> processedContent = new HashSet<>();
    private final Set<ContentCapability> createdContent = new HashSet<>();
    private final Set<ContentCapability> deletedContent = new HashSet<>();

    private final Set<ResourceCapability> usedResources = new HashSet<>();

    @Override
    public Capabilities.Builder processesAnnotation(AnnotationCapability capability) {
      processedAnnotations.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder createsAnnotation(AnnotationCapability capability) {
      createdAnnotations.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder deletesAnnotation(AnnotationCapability capability) {
      deletedAnnotations.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder processesGroup(GroupCapability capability) {
      processedGroups.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder createsGroup(GroupCapability capability) {
      createdGroups.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder deletesGroup(GroupCapability capability) {
      deletedGroups.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder processesContent(ContentCapability capability) {
      processedContent.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder createsContent(ContentCapability capability) {
      createdContent.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder deletesContent(ContentCapability capability) {
      deletedContent.add(capability);
      return this;
    }

    @Override
    public Capabilities.Builder usesResource(ResourceCapability capability) {
      usedResources.add(capability);
      return this;
    }

    @Override
    public Capabilities save() {
      return new SetCapabilities(
          Collections.unmodifiableSet(processedAnnotations),
          Collections.unmodifiableSet(createdAnnotations),
          Collections.unmodifiableSet(deletedAnnotations),
          Collections.unmodifiableSet(processedGroups),
          Collections.unmodifiableSet(createdGroups),
          Collections.unmodifiableSet(deletedGroups),
          Collections.unmodifiableSet(processedContent),
          Collections.unmodifiableSet(createdContent),
          Collections.unmodifiableSet(deletedContent),
          Collections.unmodifiableSet(usedResources));
    }
  }
}
