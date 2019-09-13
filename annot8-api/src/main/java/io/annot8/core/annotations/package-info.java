/**
 * This package contains the core interfaces for annotations and groups of annotations.
 *
 * <p>An Annotation is a marking which indicates that a region of some content has some special
 * properties or meaning. For instance, it might indicate that a span of text is a mention of an
 * Entity (for example a Person).
 *
 * <p>A Group is a collection of annotations. For example, a group could represent a relationship
 * between annotations, an event containing multiple annotations, or indicate that a collection of
 * annotations refer to the same entity. Within a group, all annotations must have a role assigned
 * (although they could all be assigned the same role).
 */
package io.annot8.core.annotations;
