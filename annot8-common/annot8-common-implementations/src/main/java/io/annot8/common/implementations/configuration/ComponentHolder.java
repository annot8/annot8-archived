/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.configuration;

import java.util.*;

import io.annot8.common.utils.java.CollectionUtils;
import io.annot8.core.components.Annot8Component;
import io.annot8.core.settings.Settings;

public class ComponentHolder<T extends Annot8Component> {

  private final List<T> components = new LinkedList<>();

  public ComponentHolder add(final T t) {
    components.add(t);
    return this;
  }

  public List<T> getComponents() {
    return Collections.unmodifiableList(components);
  }
}
