/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.settings;

/** An empty Settings class. */
public final class NoSettings implements Settings {

  private static final NoSettings INSTANCE = new NoSettings();

  private NoSettings() {
    // No public constructor for singleton
  }

  /**
   * Get the instance
   *
   * @return instance of empty settings
   */
  public static NoSettings getInstance() {
    return INSTANCE;
  }

  @Override
  public boolean validate() {
    return true;
  }
}
