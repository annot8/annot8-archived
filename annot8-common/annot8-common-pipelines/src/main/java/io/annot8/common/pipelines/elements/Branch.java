/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

import io.annot8.core.components.Annot8Component;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.helpers.WithProcessItem;

public interface Branch extends Annot8Component {

  boolean forward(Item item) throws Annot8Exception;

  void addOutput(String key, WithProcessItem queue);

  void removeOutput(String key);
}
