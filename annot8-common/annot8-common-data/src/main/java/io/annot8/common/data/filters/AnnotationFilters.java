package io.annot8.common.data.filters;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.filters.AnnotationFilter;

import java.io.StringBufferInputStream;

public class AnnotationFilters {

    public static AnnotationFilter byType(String type) {
        return new SimpleAnnotationFilter(type, null);
    }

    public static <B extends Bounds> AnnotationFilter byBounds(Class<B> bounds) {
        return new SimpleAnnotationFilter(null, bounds);
    }

    public static <B extends Bounds> AnnotationFilter byTypeAndBounds(String type, Class<B> bounds) {
        return new SimpleAnnotationFilter(null, bounds);
    }

    private static class SimpleAnnotationFilter implements AnnotationFilter {

        private String type;
        private Class<? extends Bounds> boundsClass;

        public SimpleAnnotationFilter(String type,  Class<? extends Bounds> boundsClass) {
            this.type = type;
            this.boundsClass = boundsClass;
        }

        public boolean test(Annotation annotation) {
            return (type != null && annotation.getType().equals(type))
                    && (boundsClass != null && annotation.getBounds() != null && annotation.getBounds().getClass().isInstance(boundsClass));
        }
    }
}
