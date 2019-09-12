/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.stores;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.annot8.core.annotations.Group;
import io.annot8.core.data.Item;
import io.annot8.core.filters.Filter;
import io.annot8.core.helpers.WithFilter;

/** Base groups interface from which all other group stores extend. */
public interface GroupStore extends WithFilter<Group> {

  /**
   * Get the item to which the groups relate.
   *
   * @return item
   */
  Item getItem();

  /**
   * Create a new group builder
   *
   * @return a builder object for the supported group
   */
  Group.Builder getBuilder();

  /**
   * Create a new group
   *
   * @return a builder to createContent a new group
   */
  default Group.Builder create() {
    return getBuilder();
  }

  /**
   * Clone an existing group to createContent a new group
   *
   * @param existing the existing group
   * @return a builder to based on the an existing group, but don't overwrite that group on save.
   */
  default Group.Builder copy(Group existing) {
    return getBuilder().from(existing).newId();
  }

  /**
   * Edit an existing group, saving will replace it
   *
   * @param existing the existing group
   * @return a builder to edit an existing group
   */
  default Group.Builder edit(Group existing) {
    return getBuilder().from(existing);
  }

  /**
   * Delete a group from the store
   *
   * @param group to delete
   */
  void delete(final Group group);

  /**
   * Delete a collection of groups from the store
   *
   * @param groups the groups to delete
   */
  default void delete(final Collection<Group> groups) {
    groups.forEach(this::delete);
  }

  /** Delete all groups from the store */
  default void deleteAll() {
    delete(getAll().collect(Collectors.toList()));
  }

  /**
   * Get all groups
   *
   * @return all groups currently held in this store
   */
  Stream<Group> getAll();

  /**
   * Get all groups of a given type currently held in this store
   *
   * @param type the type to filter on
   * @return the groups of that types
   */
  default Stream<Group> getByType(final String type) {
    return getAll().filter(a -> type.equals(a.getType()));
  }

  /**
   * Get the group with the given ID, if it is currently held in this store
   *
   * @param groupId the id
   * @return the group
   */
  Optional<Group> getById(final String groupId);

  /**
   * Filter annotations to match the test.
   *
   * @param filter the test to filter with
   * @return stream of matching annotations
   */
  default Stream<Group> filter(Filter<Group> filter) {
    return getAll().filter(filter::test);
  }
}
