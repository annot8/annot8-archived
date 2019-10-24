/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.annotations;

import io.annot8.api.data.Content;
import io.annot8.api.helpers.WithId;
import io.annot8.api.helpers.WithProperties;
import io.annot8.api.helpers.WithType;
import io.annot8.api.helpers.builders.WithFromBuilder;
import io.annot8.api.helpers.builders.WithIdBuilder;
import io.annot8.api.helpers.builders.WithNewIdBuilder;
import io.annot8.api.helpers.builders.WithPropertiesBuilder;
import io.annot8.api.helpers.builders.WithSave;
import io.annot8.api.helpers.builders.WithTypeBuilder;
import io.annot8.api.references.AnnotationReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Base annotation interface from which all other annotations extend. */
public interface Group extends WithId, WithType, WithProperties {

  /**
   * The roles with the associated annotations in this group
   *
   * @return a map of all roles
   */
  default Map<String, Stream<Annotation>> getAnnotations() {
    return getReferences().entrySet().stream()
        .collect(
            Collectors.toMap(Entry::getKey, e -> AnnotationReference.toAnnotations(e.getValue())));
  }

  /**
   * The associated annotations references in this group
   *
   * @return a map of all references
   */
  Map<String, Stream<AnnotationReference>> getReferences();

  /**
   * All the annotations in this group with the specified role
   *
   * @param role the role required
   * @return stream of annotations having that role
   */
  default Stream<Annotation> getAnnotations(final String role) {
    if (getAnnotations().containsKey(role)) {
      return getAnnotations().get(role);
    }
    return Stream.empty();
  }

  /**
   * Return all the roles currently associated with annotations in this group
   *
   * @return distinct roles
   */
  default Stream<String> getRoles() {
    return getReferences().keySet().stream().distinct();
  }

  /**
   * Get the role of a specific annotation in this group
   *
   * @param annotation the annotation to look for
   * @return the role for a specific annotation, or empty if the annotation is not found
   */
  Optional<String> getRole(final Annotation annotation);

  /**
   * Return all the annotations in this group for the specified content.
   *
   * @param content the content
   * @return annotations in that content
   */
  default Stream<Annotation> getAnnotationsForContent(Content content) {
    return getAnnotations().values().stream()
        .flatMap(s -> s)
        .filter(a -> content.getId().equals(a.getContentId()));
  }

  /**
   * Return all the annotations in this group for the specified content and role.
   *
   * @param content the content to limit to
   * @param role the role to filter on
   * @return annotation in that content of that role
   */
  default Stream<Annotation> getAnnotationsForContentAndRole(Content content, String role) {
    return getAnnotations(role).filter(a -> content.getId().equals(a.getContentId()));
  }

  /**
   * Check if the annotation is included in the group.
   *
   * @param annotation the annotation to look for
   * @return true if this group contains the specified annotation
   */
  boolean containsAnnotation(final Annotation annotation);

  /**
   * Check if role is included in the group
   *
   * @param role the role to look for
   * @return true if this group contains at least one annotation with the specified role
   */
  default boolean containsRole(final String role) {
    return getRoles().anyMatch(role::equals);
  }

  /**
   * Do two instances represent the same underlying group?
   *
   * <p>That is do they have the same id, even if they are different revisions.
   *
   * @param other the annotation to check against
   * @return true if they are the same (id)
   */
  default boolean sameGroup(Group other) {
    return other != null && getId().equals(other.getId());
  }

  /** Builder interface to createContent (immutable) Group classes */
  interface Builder
      extends WithIdBuilder<Builder>,
          WithTypeBuilder<Builder>,
          WithPropertiesBuilder<Builder>,
          WithNewIdBuilder<Builder>,
          WithFromBuilder<Builder, Group>,
          WithSave<Group> {

    /**
     * Add an annotation to this group with the specified role.
     *
     * @param role the role within the group
     * @param annotation the annotation to add
     * @return this builder for chaining
     */
    Builder withAnnotation(final String role, final Annotation annotation);
  }
}
