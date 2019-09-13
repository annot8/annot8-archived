/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.capabilities;

/**
 * Describes a group that is created, processed or deleted
 */
public interface GroupCapability extends Capability {
  /**
   * The type of group
   */
  String getType();
}
