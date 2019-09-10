/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory.configuration;

import java.util.Set;

import io.annot8.core.settings.Settings;

public interface ComponentConfiguration {

  String getName();

  String getComponent();

  Settings getSettings();
}
