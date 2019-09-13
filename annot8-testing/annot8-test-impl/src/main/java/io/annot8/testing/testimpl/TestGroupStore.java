/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.testing.testimpl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import io.annot8.common.implementations.delegates.DelegateGroupBuilder;
import io.annot8.common.implementations.factories.GroupBuilderFactory;
import io.annot8.core.annotations.Group;
import io.annot8.core.annotations.Group.Builder;
import io.annot8.core.data.Item;
import io.annot8.core.stores.GroupStore;

public class TestGroupStore implements GroupStore {

  private final Map<String, Group> groups = new ConcurrentHashMap<>();
  private final GroupBuilderFactory groupBuilderFactory;
  private Item item;

  public TestGroupStore() {
    this(null);
  }

  public TestGroupStore(Item item, GroupBuilderFactory groupBuilderFactory) {
    this.item = item;
    this.groupBuilderFactory = groupBuilderFactory;
  }

  public TestGroupStore(Item item) {
    this(item, TestGroupBuilder.factory());
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Item getItem() {
    return item;
  }

  @Override
  public Builder getBuilder() {
    return new DelegateGroupBuilder(groupBuilderFactory.create(item, this)) {
      @Override
      public Group save() {
        return TestGroupStore.this.save(super.save());
      }
    };
  }

  public Group save(Builder groupBuilder) {
    Group group = groupBuilder.save();
    return save(group);
  }

  public Group save(Group group) {
    groups.put(group.getId(), group);
    return group;
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
    return Optional.ofNullable(groups.get(groupId));
  }
}
