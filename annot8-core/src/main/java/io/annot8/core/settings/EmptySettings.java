/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.settings;

/** An empty Settings class. */
public final class EmptySettings implements Settings {

  private static final EmptySettings INSTANCE = new EmptySettings();

  private EmptySettings() {
    // No public constructor for singleton
  }

  /**
   * Get the instance
   *
   * @return instance of empty settings
   */
  public static EmptySettings getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean validate() {
    return true;
  }
}
