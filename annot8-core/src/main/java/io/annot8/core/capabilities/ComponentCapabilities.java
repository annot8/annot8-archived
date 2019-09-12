/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import java.util.stream.Stream;

public interface ComponentCapabilities {
  default Stream<AnnotationCapability> createsAnnotations() {
    return Stream.empty();
  }

  default Stream<AnnotationCapability> processesAnnotations() {
    return Stream.empty();
  }

  default Stream<AnnotationCapability> deletesAnnotations() {
    return Stream.empty();
  }

  default Stream<ContentCapability> createsContent() {
    return Stream.empty();
  }

  default Stream<ContentCapability> processesContent() {
    return Stream.empty();
  }

  default Stream<ContentCapability> deletesContent() {
    return Stream.empty();
  }

  default Stream<GroupCapability> createsGroups() {
    return Stream.empty();
  }

  default Stream<GroupCapability> processesGroups() {
    return Stream.empty();
  }

  default Stream<GroupCapability> deletesGroups() {
    return Stream.empty();
  }

  default boolean createsItems() {
    return false;
  }
}
