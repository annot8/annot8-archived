/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.helpers;

import io.annot8.api.stores.GroupStore;

/** Helper interface to indicate that a class supports GroupStore */
public interface WithGroups {

  /**
   * Get the group store
   *
   * @return the groups contained within this object
   */
  GroupStore getGroups();
}
