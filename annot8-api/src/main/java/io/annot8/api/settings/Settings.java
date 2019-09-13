/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.api.settings;

/** Base settings interface from which all other settings extend. */
public interface Settings {

  /**
   * Check that the settings are sufficiently complete to allow them to be used.
   *
   * <p>You should log any errors in validation.
   *
   * <p>Validation will be ignored/superceded by more strict validation by the caller (for example,
   * the caller might offer defaults if data is missing). However the implementation should be
   * sufficiently robust that the use of settings instance as-is would not cause null pointers or
   * obvious errors.
   *
   * @return true is the settings are valid
   */
  boolean validate();
}
