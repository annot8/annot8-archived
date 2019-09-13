/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.annot8.api.bounds.Bounds;

public class AnnotationTest {

  @Test
  public void testGetBounds() {
    Bounds bounds = Mockito.mock(TestBounds.class);
    Annotation annotation = Mockito.mock(Annotation.class);
    doReturn(bounds).when(annotation).getBounds();
    doCallRealMethod().when(annotation).getBounds(Mockito.any());

    Optional<TestBounds> result = annotation.getBounds(TestBounds.class);
    assertTrue(result.isPresent());
    assertEquals(bounds, result.get());

    Optional<TestBounds2> result2 = annotation.getBounds(TestBounds2.class);
    assertFalse(result2.isPresent());
  }

  @Test
  public void sameAnnotation() {
    String annotationId1 = "annId1";
    String annotationId2 = "annId2";
    Annotation ann1 = new TestAnnotation(annotationId1, null, null, null, null);
    Annotation ann2 = new TestAnnotation(annotationId1, null, null, null, null);
    Annotation ann3 = new TestAnnotation(annotationId2, null, null, null, null);

    assertTrue(ann1.sameAnnotation(ann2));
    assertFalse(ann1.sameAnnotation(ann3));
  }

  public abstract class TestBounds implements Bounds {}

  public abstract class TestBounds2 implements Bounds {}
}
