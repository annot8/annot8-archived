/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.stores;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import io.annot8.core.data.Item;
import org.junit.jupiter.api.Test;

import io.annot8.core.annotations.Annotation;
import io.annot8.core.annotations.Group;
import io.annot8.core.annotations.Group.Builder;
import io.annot8.core.annotations.TestGroup;
import io.annot8.core.exceptions.IncompleteException;
import io.annot8.core.properties.ImmutableProperties;
import io.annot8.core.properties.Properties;
import io.annot8.core.references.AnnotationReference;
import org.mockito.Mock;

/** Unit tests for the default method implementations on {@link GroupStore} */
public class GroupStoreTest {

  private static final String GROUP_ID = "testGroupId";

  Item item = null;

  @Test
  public void testCreate() {
    GroupStore store = new TestGroupStore(item);
    Builder builder = store.create();
    assertNotNull(builder);
  }

  @Test
  public void testCopy() {
    TestGroup group = new TestGroup(GROUP_ID, null, null, null, null);
    GroupStore store = new TestGroupStore(item);
    Group copied = null;
    try {
      copied = store.copy(group).save();
    } catch (IncompleteException e) {
      fail("Failed to copy group");
    }
    assertNotNull(copied);
    assertNotEquals(GROUP_ID, copied.getId());
  }

  @Test
  public void testEdit() {
    TestGroup group = new TestGroup(GROUP_ID, null, null, null, null);
    GroupStore store = new TestGroupStore(item);
    Group edit = null;
    try {
      edit = store.edit(group).save();
    } catch (IncompleteException e) {
      fail("Failed to copy group");
    }
    assertNotNull(edit);
    assertEquals(GROUP_ID, edit.getId());
  }

  @Test
  public void testDeleteAll() {
    TestGroup group = new TestGroup(GROUP_ID, null, null, null, null);
    GroupStore store = new TestGroupStore(item, Collections.singleton(group));
    assertEquals(1, store.getAll().count());
    store.deleteAll();
    assertEquals(0, store.getAll().count());
  }

  @Test
  public void testDeleteCollection() {
    TestGroup group = new TestGroup(GROUP_ID, null, null, null, null);
    GroupStore store = new TestGroupStore(item, Collections.singleton(group));
    assertEquals(1, store.getAll().count());
    store.delete(Collections.singleton(group));
    assertEquals(0, store.getAll().count());
  }

  @Test
  public void testGetByType() {
    String type = "testType";
    TestGroup group = new TestGroup(GROUP_ID, type, null, null, null);
    TestGroup group2 = new TestGroup("id2", "type2", null, null, null);
    Collection<Group> groups = new ArrayList<>();
    groups.add(group);
    groups.add(group2);

    GroupStore store = new TestGroupStore(item, groups);
    assertEquals(1, store.getByType(type).count());
    assertEquals(GROUP_ID, store.getByType(type).findFirst().get().getId());
  }

  private class TestGroupStore implements GroupStore {

    private final Map<String, Group> groups;
    private Item item;

    public TestGroupStore(Item item) {
      this(item, new ArrayList<>());
    }

    public TestGroupStore(Item item, Collection<Group> groupsToAdd) {
      this.item = item;
      this.groups = new ConcurrentHashMap<>();
      groupsToAdd.forEach(g -> groups.put(g.getId(), g));
    }

    @Override
    public Item getItem() {
      return item;
    }

    @Override
    public Builder getBuilder() {
      return new TestGroupBuilder();
    }

    @Override
    public void delete(Group group) {
      groups.remove(group.getId());
    }

    @Override
    public Stream<Group> getAll() {
      return groups.values().stream();
    }

    @Override
    public Optional<Group> getById(String groupId) {
      return Optional.of(groups.get(groupId));
    }
  }

  private class TestGroupBuilder implements Group.Builder {

    private String id;
    private String type;
    private ImmutableProperties properties;
    private String role;
    private Map<String, Collection<AnnotationReference>> references;

    @Override
    public Builder withId(String id) {
      this.id = id;
      return this;
    }

    @Override
    public Builder withType(String type) {
      this.type = type;
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
    public Builder from(Group from) {
      this.id = from.getId();
      return this;
    }

    @Override
    public Group save() {
      return new TestGroup(id, type, properties, references, role);
    }

    @Override
    public Builder withAnnotation(String role, Annotation annotation) {
      return this;
    }
  }
}
