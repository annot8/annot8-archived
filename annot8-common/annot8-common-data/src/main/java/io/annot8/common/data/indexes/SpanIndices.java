package io.annot8.common.data.indexes;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.annot8.common.data.bounds.SpanBounds;
import io.annot8.core.annotations.Annotation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpanIndices {

    public static Multimap<Annotation, Annotation> topDown(List<Annotation> over, List<Annotation> under) {
        HashMultimap<Annotation, Annotation> multimap = HashMultimap.create();

        for(Annotation o : over) {

            Optional<SpanBounds> oBounds = o.getBounds(SpanBounds.class);

            if(oBounds.isPresent()) {
                List<Annotation> found = findEnclosedAnnotations(oBounds.get(), under);

                if(!found.isEmpty()) {
                    multimap.putAll(o, found);
                }
            }
        }

        return multimap;
    }

    private static List<Annotation> findEnclosedAnnotations(SpanBounds bounds, List<Annotation> list) {

        return list.stream()
                .filter(a -> {
                    Optional<SpanBounds> b = a.getBounds(SpanBounds.class);
                    if(b.isEmpty()) {
                        return false;
                    }

                    return bounds.isWithin(b.get());
                }).collect(Collectors.toList());
    }

    public static Multimap<Annotation, Annotation> bottomUp(List<Annotation> under, List<Annotation> over) {
        HashMultimap<Annotation, Annotation> multimap = HashMultimap.create();

        for(Annotation u : under) {

            Optional<SpanBounds> oBounds = u.getBounds(SpanBounds.class);

            if(oBounds.isPresent()) {
                List<Annotation> found = findInsideAnnotations(oBounds.get(), over);

                if(!found.isEmpty()) {
                    multimap.putAll(u, found);
                }
            }
        }

        return multimap;
    }

    private static List<Annotation> findInsideAnnotations(SpanBounds bounds, List<Annotation> list) {

        return list.stream()
                .filter(a -> {
                    Optional<SpanBounds> b = a.getBounds(SpanBounds.class);
                    if(b.isEmpty()) {
                        return false;
                    }

                    return b.get().isWithin(bounds);
                }).collect(Collectors.toList());
    }
}
