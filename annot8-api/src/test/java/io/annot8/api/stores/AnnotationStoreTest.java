/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.stores;

import static org.junit.jupiter.api.Assertions.*;

import io.annot8.api.annotations.Annotation;
import io.annot8.api.annotations.Annotation.Builder;
import io.annot8.api.annotations.TestAnnotation;
import io.annot8.api.bounds.Bounds;
import io.annot8.api.data.Content;
import io.annot8.api.exceptions.IncompleteException;
import io.annot8.api.properties.ImmutableProperties;
import io.annot8.api.properties.Properties;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/** Unit tests for the default method implementations on {@link AnnotationStore} */
public class AnnotationStoreTest {

  Content<?> content = null;

  @Test
  public void testCreate() {
    AnnotationStore store = new TestAnnotationStore(content);
    Annotation.Builder builder = store.create();
    assertNotNull(builder);
  }

  @Test
  public void testCopy() {
    String testId = "testId";
    AnnotationStore store = new TestAnnotationStore(content);
    Builder builder = store.copy(new TestAnnotation(testId, null, null, null, null));
    Annotation copied = null;
    try {
      copied = builder.save();
    } catch (IncompleteException e) {
      fail("Error when creating anntotation");
    }
    assertNotEquals(testId, copied.getId());
  }

  @Test
  public void testEdit() {
    String testId = "testId";
    AnnotationStore store = new TestAnnotationStore(content);
    Annotation annotation = new TestAnnotation(testId, null, null, null, null);

    Annotation edit = null;
    try {
      edit = store.edit(annotation).save();
    } catch (IncompleteException e) {
      fail("Error when creating anntotation");
    }

    assertEquals(testId, edit.getId());
  }

  @Test
  public void testDelete() {
    String testId = "testId";
    Annotation annotation = new TestAnnotation(testId, null, null, null, null);
    AnnotationStore store = new TestAnnotationStore(content, Collections.singleton(annotation));
    assertEquals(1, store.getAll().count());
    store.delete(annotation);
    assertEquals(0, store.getAll().count());
  }

  @Test
  public void testDeleteAll() {
    Annotation annotation = new TestAnnotation("test", null, null, null, null);
    AnnotationStore store = new TestAnnotationStore(content, Collections.singleton(annotation));
    assertEquals(1, store.getAll().count());
    store.deleteAll();
    assertEquals(0, store.getAll().count());
  }

  @Test
  public void testDeleteCollection() {
    Annotation annotation = new TestAnnotation("test", null, null, null, null);
    Collection<Annotation> collection = Collections.singleton(annotation);
    AnnotationStore store = new TestAnnotationStore(content, collection);
    assertEquals(1, store.getAll().count());
    store.delete(collection);
    assertEquals(0, store.getAll().count());
  }

  @Test
  public void testGetByType() {
    String id1 = "id1";
    String id2 = "id2";
    Annotation annotation = new TestAnnotation(id1, "type", null, null, null);
    Annotation annotation2 = new TestAnnotation(id2, "type2", null, null, null);
    List<Annotation> annotations = new ArrayList<>();
    annotations.add(annotation);
    annotations.add(annotation2);

    AnnotationStore store = new TestAnnotationStore(content, annotations);

    assertEquals(1, store.getByType("type").count());
    assertEquals(1, store.getByType("type2").count());
    assertEquals(id1, store.getByType("type").findFirst().get().getId());
    assertEquals(id2, store.getByType("type2").findFirst().get().getId());
  }

  @Test
  public void testGetByBounds() {
    String id1 = "id1";
    String id2 = "id2";
    Annotation annotation =
        new TestAnnotation(id1, null, null, Mockito.mock(TestBounds.class), null);
    Annotation annotation2 =
        new TestAnnotation(id2, null, null, Mockito.mock(TestBounds2.class), null);
    List<Annotation> annotations = new ArrayList<>();
    annotations.add(annotation);
    annotations.add(annotation2);

    TestAnnotationStore store = new TestAnnotationStore(content, annotations);
    assertEquals(1, store.getByBounds(TestBounds.class).count());
    assertEquals(1, store.getByBounds(TestBounds2.class).count());
    assertEquals(id1, store.getByBounds(TestBounds.class).findFirst().get().getId());
    assertEquals(id2, store.getByBounds(TestBounds2.class).findFirst().get().getId());
  }

  @Test
  public void testFilter() {
    String id1 = "id1";
    String id2 = "id2";
    Annotation annotation =
        new TestAnnotation(id1, null, null, Mockito.mock(TestBounds.class), null);
    Annotation annotation2 =
        new TestAnnotation(id2, null, null, Mockito.mock(TestBounds2.class), null);
    List<Annotation> annotations = new ArrayList<>();
    annotations.add(annotation);
    annotations.add(annotation2);

    TestAnnotationStore store = new TestAnnotationStore(content, annotations);

    assertEquals(2, store.filter(a -> true).count());
    assertEquals(1, store.filter(a -> a.getId().equals("id2")).count());
  }

  public abstract class TestBounds implements Bounds {}

  public abstract class TestBounds2 implements Bounds {}

  private class TestAnnotationStore implements AnnotationStore {

    private final Map<String, Annotation> annotations;
    private final Content<?> content;

    public TestAnnotationStore(Content<?> content) {
      this(content, Collections.emptyList());
    }

    public TestAnnotationStore(Content<?> content, Collection<Annotation> annotationsToAdd) {
      this.content = content;
      this.annotations = new ConcurrentHashMap<>();
      annotationsToAdd.forEach(a -> annotations.put(a.getId(), a));
    }

    @Override
    public Content<?> getContent() {
      return content;
    }

    @Override
    public Builder getBuilder() {
      return new TestAnnotationBuilder();
    }

    @Override
    public void delete(Annotation annotation) {
      annotations.remove(annotation.getId());
    }

    @Override
    public Stream<Annotation> getAll() {
      return annotations.values().stream();
    }

    @Override
    public Optional<Annotation> getById(String annotationId) {
      return Optional.of(annotations.get(annotationId));
    }
  }

  // Stub AnnotationBuilder with only the implementation required for the above tests
  private class TestAnnotationBuilder implements Annotation.Builder {

    private String id;
    private String type;
    private ImmutableProperties properties;

    @Override
    public Builder withType(String type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder withId(String id) {
      return this;
    }

    @Override
    public Builder withProperty(String key, Object value) {
      return this;
    }

    @Override
    public Builder withPropertyIfPresent(String key, Optional<?> value) {
      return this;
    }

    @Override
    public Builder withoutProperty(String key, Object value) {
      return this;
    }

    @Override
    public Builder withoutProperty(String key) {
      return this;
    }

    @Override
    public Builder withProperties(Properties properties) {
      return this;
    }

    @Override
    public Builder newId() {
      this.id = UUID.randomUUID().toString();
      return this;
    }

    @Override
    public Builder from(Annotation from) {
      this.id = from.getId();
      return this;
    }

    @Override
    public Annotation save() {
      return new TestAnnotation(
          id, type, Mockito.mock(ImmutableProperties.class), Mockito.mock(Bounds.class), null);
    }

    @Override
    public Builder withBounds(Bounds bounds) {
      return this;
    }
  }
}
