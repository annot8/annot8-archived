/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.registries;

import io.annot8.api.components.Source;
import io.annot8.api.components.responses.SourceResponse;
import io.annot8.api.data.ItemFactory;

public class TestSource implements Source {

  @Override
  public SourceResponse read(ItemFactory itemFactory) {
    return SourceResponse.done();
  }
}
