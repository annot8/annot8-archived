/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.base;

import java.util.HashMap;
import java.util.Map;

import io.annot8.common.pipelines.elements.Branch;
import io.annot8.core.data.Item;
import io.annot8.core.exceptions.Annot8Exception;
import io.annot8.core.helpers.WithProcessItem;

public abstract class AbstractBranch implements Branch {

  private final Map<String, WithProcessItem> branches = new HashMap<>();

  @Override
  public void addOutput(String key, WithProcessItem queue) {
    branches.put(key, queue);
  }

  @Override
  public void removeOutput(String key) {
    branches.remove(key);
  }

  protected boolean sendToBranch(Item item, String key) throws Annot8Exception {
    final WithProcessItem consumer = branches.get(key);
    if (consumer == null) {
      return false;
    } else {
      consumer.process(item);
      return true;
    }
  }

  protected boolean sendToBranches(Item item, String... keys) throws Annot8Exception {
    if (keys.length == 0) {
      return true;
    }

    long found = 0;
    for (String k : keys) {
      if (sendToBranch(item, k)) {
        found++;
      }
    }

    // As long as we've sent it somewhere..
    return found > 0;
  }
}
