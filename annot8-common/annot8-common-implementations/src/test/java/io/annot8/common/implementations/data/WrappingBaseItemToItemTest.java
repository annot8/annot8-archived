/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.implementations.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Item;

@ExtendWith(MockitoExtension.class)
class WrappingBaseItemToItemTest {

  @Mock BaseItemFactory itemFactory;

  @Mock BaseItem item;

  @Test
  void processesNonNullItem() {
    final Item converted = new WrappingBaseItemToItem(itemFactory).convert(item);
    assertThat(converted).isNotNull();
  }
}
