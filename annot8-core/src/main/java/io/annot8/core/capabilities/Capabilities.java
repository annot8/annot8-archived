/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.capabilities;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import io.annot8.core.bounds.Bounds;
import io.annot8.core.components.Resource;
import io.annot8.core.data.Content;
import io.annot8.core.helpers.builders.WithSave;

/**
 * Base capabilities interface used to describe the capabilities of a component.
 *
 * <p>In all cases an empty stream should be returned (rather than null)
 */
public interface Capabilities {

  /**
   * The type of any input annotations (i.e. annotations that must be present before a component can
   * work)
   *
   * @return annotation specifications
   */
  Stream<AnnotationCapability> getProcessedAnnotations();

  /**
   * The type of any output annotations produced by this component
   *
   * @return annotation specifications
   */
  Stream<AnnotationCapability> getCreatedAnnotations();

  /**
   * The type of any output annotations deleted by this component
   *
   * @return annotation specifications
   */
  Stream<AnnotationCapability> getDeletedAnnotations();

  /**
   * The type of any required input annotations (i.e. annotations that must be present before a
   * component can work)
   *
   * @return annotation specifications
   */
  Stream<GroupCapability> getProcessedGroups();

  /**
   * The type of any output annotations produced by this component
   *
   * @return group specifications
   */
  Stream<GroupCapability> getCreatedGroups();

  /**
   * The type of any output annotations deleted by this component
   *
   * @return group specifications
   */
  Stream<GroupCapability> getDeletedGroups();

  /**
   * The content classes produced by this component, or an empty stream if no new content will be
   * produced
   *
   * @return content specifications
   */
  Stream<ContentCapability> getCreatedContent();

  /**
   * The type of any deleted content
   *
   * @return content specifications
   */
  Stream<ContentCapability> getDeletedContent();

  /**
   * The type of any required content (i.e. content that must be present before a component can
   * work)
   *
   * @return content specifications
   */
  Stream<ContentCapability> getProcessedContent();

  /**
   * The resource classes required by this component
   *
   * @return resource specifications
   */
  Stream<ResourceCapability> getUsedResources();

  /** Builder for capabilties */
  interface Builder extends WithSave<Capabilities> {

    /**
     * Declare that the component will process an annotation
     *
     * @param type annotation type
     * @param clazz bounds class
     * @param optional true if the component can function and generated output without this
     * @return the builder for chaining
     */
    default Builder processesAnnotation(
        String type, Class<? extends Bounds> clazz, boolean optional) {
      return processesAnnotation(new AnnotationCapability(type, clazz, optional));
    }

    /**
     * Declare that the component will create an annotation
     *
     * @param type annotation type
     * @param clazz bounds class
     * @return the builder for chaining
     */
    default Builder createsAnnotation(String type, Class<? extends Bounds> clazz) {
      return createsAnnotation(new AnnotationCapability(type, clazz, true));
    }

    /**
     * Declare that the component will delete an annotation
     *
     * @param type annotation type
     * @param clazz bounds class
     * @return the builder for chaining
     */
    default Builder deletesAnnotation(String type, Class<? extends Bounds> clazz) {
      return deletesAnnotation(new AnnotationCapability(type, clazz, true));
    }

    /**
     * Declare that the component will process a group
     *
     * @param type group type
     * @param optional true if the component can function and generated output without this
     * @return the builder for chaining
     */
    default Builder processesGroup(String type, boolean optional) {
      return processesGroup(new GroupCapability(type, true));
    }

    /**
     * Declare that the component will create a group
     *
     * @param type annotation type
     * @return the builder for chaining
     */
    default Builder createsGroup(String type) {
      return createsGroup(new GroupCapability(type, true));
    }

    /**
     * Declare that the component will delete a group
     *
     * @param type annotation type
     * @return the builder for chaining
     */
    default Builder deletesGroup(String type) {
      return deletesGroup(new GroupCapability(type, true));
    }

    /**
     * Declare that the component will process a type of content
     *
     * @param clazz content type
     * @param optional true if the component can function and generated output without this
     * @return the builder for chaining
     */
    default Builder processesContent(Class<? extends Content<?>> clazz, boolean optional) {
      return processesContent(new ContentCapability(clazz, optional));
    }

    /**
     * Declare that the component will create a type of content
     *
     * @param clazz content type
     * @return the builder for chaining
     */
    default Builder createsContent(Class<? extends Content<?>> clazz) {
      return createsContent(new ContentCapability(clazz, true));
    }

    /**
     * Declare that the component will delete a type of content
     *
     * @param clazz content type
     * @return the builder for chaining
     */
    default Builder deletesContent(Class<? extends Content<?>> clazz) {
      return deletesContent(new ContentCapability(clazz, true));
    }

    /**
     * Declare that the component will use a resource
     *
     * @param clazz resorce type
     * @param optional true if the component can function and generated output without this
     * @return the builder for chaining
     */
    default Builder usesResource(Class<? extends Resource> clazz, boolean optional) {
      return usesResource(new ResourceCapability(clazz, true));
    }

    /**
     * Declare that the component will process an annotation
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder processesAnnotation(AnnotationCapability capability);

    /**
     * Declare that the component will create an annotation
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder createsAnnotation(AnnotationCapability capability);

    /**
     * Declare that the component will delete an annotation
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder deletesAnnotation(AnnotationCapability capability);

    /**
     * Declare that the component will process a group
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder processesGroup(GroupCapability capability);

    /**
     * Declare that the component will create a group
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder createsGroup(GroupCapability capability);

    /**
     * Declare that the component will delete a group
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder deletesGroup(GroupCapability capability);

    /**
     * Declare that the component will process a content type
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder processesContent(ContentCapability capability);

    /**
     * Declare that the component will create a content type
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder createsContent(ContentCapability capability);

    /**
     * Declare that the component will delete a content type
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder deletesContent(ContentCapability capability);

    /**
     * Declare that the component will use a resource
     *
     * @param capability capability definition
     * @return the builder for chaining
     */
    Builder usesResource(ResourceCapability capability);

    /**
     * Save content of the builder into an capabilities
     *
     * <p>Note this does not throw an exception (hence overrides the WithSave)
     *
     * @return the object being built by this builder
     */
    Capabilities save();

    /**
     * Merge another set of existing capabilities.
     *
     * @param capabilities the capabilities to add
     * @return this builder for chaining
     */
    default Builder merge(Capabilities capabilities) {

      if (capabilities != null) {
        applySafely(capabilities.getCreatedContent(), this::createsContent);
        applySafely(capabilities.getCreatedAnnotations(), this::createsAnnotation);
        applySafely(capabilities.getCreatedGroups(), this::createsGroup);

        applySafely(capabilities.getProcessedAnnotations(), this::processesAnnotation);
        applySafely(capabilities.getProcessedContent(), this::processesContent);
        applySafely(capabilities.getProcessedGroups(), this::processesGroup);

        applySafely(capabilities.getUsedResources(), this::usesResource);

        applySafely(capabilities.getDeletedAnnotations(), this::deletesAnnotation);
        applySafely(capabilities.getDeletedGroups(), this::deletesGroup);
        applySafely(capabilities.getDeletedContent(), this::deletesContent);
      }

      return this;
    }

    /**
     * Applies the consumer to each non-null member of the stream.
     *
     * @param stream stream of elements (may be null, and contain nulls)
     * @param consumer applied to elements
     * @param <T> the type of element
     */
    private <T> void applySafely(Stream<T> stream, Consumer<T> consumer) {
      if (stream == null || consumer == null) {
        return;
      }

      stream.filter(Objects::nonNull).forEach(consumer);
    }
  }
}
