/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.references;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.annot8.core.annotations.Annotation;

class AnnotationReferenceTest {

  @Test
  void toAnnotationWithNull() {
    assertThat(AnnotationReference.toAnnotations(null).count()).isEqualTo(0);
  }

  @Test
  void toAnnotation() {

    final Stream<AnnotationReference> input =
        Stream.of(
            createMockAnnotation(),
            createMockAnnotation(),
            createMockAnnotation(),
            createMissingAnnotation());

    final List<Annotation> output =
        AnnotationReference.toAnnotations(input).collect(Collectors.toList());

    assertThat(output).hasSize(3);
  }

  private AnnotationReference createMissingAnnotation() {
    AnnotationReference reference = Mockito.mock(AnnotationReference.class);
    when(reference.toAnnotation()).thenReturn(Optional.empty());
    return reference;
  }

  private AnnotationReference createMockAnnotation() {
    AnnotationReference reference = Mockito.mock(AnnotationReference.class);
    Annotation a = Mockito.mock(Annotation.class);
    when(reference.toAnnotation()).thenReturn(Optional.of(a));
    return reference;
  }
}
