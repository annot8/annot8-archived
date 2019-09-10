package io.annot8.core.filters;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.annotations.Group;

import java.util.function.Predicate;

/** Filter groups */
@FunctionalInterface
public interface GroupFilter extends Predicate<Group> {
}
