/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.capabilities;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.ImmutableSetMultimap.Builder;

import io.annot8.core.settings.EmptySettings;
import io.annot8.core.settings.Settings;
import io.annot8.core.settings.SettingsClass;

/** Collates all settings class for components based on the SettingsClass annotation. */
public class SettingsCompiler {

  /**
   * Collate settings for a component into a single map
   *
   * @param component the component to consider
   * @return the (immutable) map of parent class to the settings it declares
   */
  public Map<Class<?>, Collection<Class<? extends Settings>>> compile(Class<?> component) {

    Builder<Class<?>, Class<? extends Settings>> builder = ImmutableSetMultimap.builder();

    addAnnotatedSettings(builder, component);

    return builder.build().asMap();
  }

  /**
   * Collate seetings for a component (and its parent classes) into a set
   *
   * @param component the component to consider
   * @return the (immutable) set settings declared
   */
  public Set<Class<? extends Settings>> compileAsSet(Class<?> component) {

    Collection<Collection<Class<? extends Settings>>> values = compile(component).values();

    if (values.isEmpty()) {
      return Collections.emptySet();
    }

    return values.stream().flatMap(Collection::stream).collect(Collectors.toSet());
  }

  protected void addAnnotatedSettings(
      Builder<Class<?>, Class<? extends Settings>> classes, Class<?> clazz) {
    // Recurse through parents
    Class<?> superclass = clazz.getSuperclass();
    if (superclass != null) {
      addAnnotatedSettings(classes, superclass);
    }

    SettingsClass[] annotations = clazz.getAnnotationsByType(SettingsClass.class);

    Arrays.stream(annotations)
        // Discard anything which is effectively 'no settings'
        .filter(a -> !Settings.class.equals(a.value()) && !EmptySettings.class.equals(a.value()))
        .forEach(a -> classes.put(clazz, a.value()));
  }
}
