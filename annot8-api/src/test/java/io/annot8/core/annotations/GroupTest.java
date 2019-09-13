/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.annotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import io.annot8.core.data.Content;
import io.annot8.core.references.AnnotationReference;

/**
 * Unit tests for the default method implementations on the {@link Group} interface.
 *
 * <p>Mockito strictness settings are relaxed to avoid unused stubbing exceptions in the reusable
 * mocking methods
 */
@MockitoSettings(strictness = Strictness.LENIENT)
public class GroupTest {

  private static final String TEST_ANNOTATION_ID = "annotationId";
  private static final String TEST_GROUP_ID = "testGroup";
  private static final String TEST_ROLE = "testRole";
  private static final String TEST_CONTENT_ID = "contentId";
  private static final String TEST_CONTENT_DESCRIPTION = "contentDescription";

  @Test
  public void testGetAnnotations() {
    String testRole2 = "testRole2";
    String annotationId2 = "annotationId2";
    AnnotationReference reference = getTestAnnotationReference(TEST_ANNOTATION_ID);
    AnnotationReference reference2 = getTestAnnotationReference(annotationId2);
    Map<String, Collection<AnnotationReference>> refs = new HashMap<>();
    refs.put(TEST_ROLE, Collections.singletonList(reference));
    refs.put(testRole2, Collections.singletonList(reference2));
    Group group = new TestGroup(TEST_GROUP_ID, null, null, refs, TEST_ROLE);

    Map<String, Stream<Annotation>> annotations = group.getAnnotations();
    assertEquals(2, annotations.size());
    assertTrue(annotations.containsKey(TEST_ROLE));
    assertTrue(annotations.containsKey(testRole2));

    List<Annotation> roleAnnotations = annotations.get(TEST_ROLE).collect(Collectors.toList());
    assertEquals(1, roleAnnotations.size());
    assertEquals(TEST_ANNOTATION_ID, roleAnnotations.get(0).getId());

    List<Annotation> roleAnnotations2 = annotations.get(testRole2).collect(Collectors.toList());
    assertEquals(1, roleAnnotations2.size());
    assertEquals(annotationId2, roleAnnotations2.get(0).getId());
  }

  @Test
  public void testGetAnnotationsForRole() {
    Group group = getDefaultTestGroup();

    List<Annotation> annotations = group.getAnnotations(TEST_ROLE).collect(Collectors.toList());
    assertEquals(1, annotations.size());
    assertEquals(TEST_ANNOTATION_ID, annotations.get(0).getId());

    List<Annotation> empty = group.getAnnotations("noRole").collect(Collectors.toList());
    assertEquals(0, empty.size());
  }

  @Test
  public void testGetRoles() {
    Group group = getDefaultTestGroup();

    List<String> roles = group.getRoles().collect(Collectors.toList());
    assertEquals(1, roles.size());
    assertEquals(TEST_ROLE, roles.get(0));
  }

  @Test
  public void testGetAnnotationsForContent() {
    Group group = getDefaultTestGroup();
    Content<?> content = Mockito.mock(Content.class);
    doReturn(TEST_CONTENT_ID).when(content).getId();
    doReturn(TEST_CONTENT_DESCRIPTION).when(content).getDescription();

    Content<?> noContent = Mockito.mock(Content.class);
    doReturn("unusedId").when(noContent).getId();
    doReturn("unusedContentDescription").when(noContent).getDescription();

    List<Annotation> annotations =
        group.getAnnotationsForContent(content).collect(Collectors.toList());
    assertEquals(1, annotations.size());
    assertEquals(TEST_ANNOTATION_ID, annotations.get(0).getId());

    assertEquals(0, group.getAnnotationsForContent(noContent).count());
  }

  @Test
  public void testGetAnnotationsForContentAndRole() {
    Group group = getDefaultTestGroup();
    Content<?> content = Mockito.mock(Content.class);
    doReturn(TEST_CONTENT_ID).when(content).getId();
    doReturn(TEST_CONTENT_DESCRIPTION).when(content).getDescription();

    Content<?> noContent = Mockito.mock(Content.class);
    doReturn("unusedContentId").when(noContent).getId();
    doReturn("unusedContentDescription").when(noContent).getDescription();

    List<Annotation> annotations =
        group.getAnnotationsForContentAndRole(content, TEST_ROLE).collect(Collectors.toList());
    assertEquals(1, annotations.size());
    assertEquals(TEST_ANNOTATION_ID, annotations.get(0).getId());

    assertEquals(0, group.getAnnotationsForContentAndRole(noContent, TEST_ROLE).count());
    assertEquals(0, group.getAnnotationsForContentAndRole(content, "noRole").count());
  }

  @Test
  public void testContainsRole() {
    Group group = getDefaultTestGroup();

    assertTrue(group.containsRole(TEST_ROLE));
    assertFalse(group.containsRole("unusedRole"));
  }

  @Test
  public void testSameGroup() {
    Group group = getDefaultTestGroup();
    Group group2 = getDefaultTestGroup();
    assertTrue(group.sameGroup(group2));

    assertFalse(group.sameGroup(new TestGroup("diffId", null, null, null, null)));
  }

  private AnnotationReference getTestAnnotationReference(String annotationId) {
    Annotation annotation = Mockito.mock(Annotation.class);
    AnnotationReference reference = Mockito.mock(AnnotationReference.class);
    doReturn(annotationId).when(annotation).getId();
    doReturn(TEST_CONTENT_ID).when(annotation).getContentId();
    doReturn(Optional.of(annotation)).when(reference).toAnnotation();
    return reference;
  }

  private Group getDefaultTestGroup() {
    AnnotationReference reference = getTestAnnotationReference(TEST_ANNOTATION_ID);
    Map<String, Collection<AnnotationReference>> refs = new HashMap<>();
    refs.put(TEST_ROLE, Collections.singletonList(reference));
    return new TestGroup(TEST_GROUP_ID, null, null, refs, TEST_ROLE);
  }
}
