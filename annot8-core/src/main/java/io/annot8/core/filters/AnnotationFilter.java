package io.annot8.core.filters;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.data.Content;

import java.util.function.Predicate;

/**
 * Filter annotations
 */
@FunctionalInterface
public interface AnnotationFilter extends Predicate<Annotation> {
}
