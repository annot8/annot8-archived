package io.annot8.common.data.filters;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.annotations.Group;
import io.annot8.core.bounds.Bounds;
import io.annot8.core.filters.AnnotationFilter;
import io.annot8.core.filters.GroupFilter;

public class GroupFilters {

    public static GroupFilter byType(String type) {
        return new SimpleGroupFilter(type);
    }

    private static class SimpleGroupFilter implements GroupFilter {
        private String type;

        public SimpleGroupFilter(String type) {
            this.type = type;
        }

        public boolean test(Group group) {
            return (type != null && group.getType().equals(type));
        }
    }
}
