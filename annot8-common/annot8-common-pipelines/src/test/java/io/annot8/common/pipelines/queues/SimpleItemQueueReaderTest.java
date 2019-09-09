/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.implementations.data.BaseItemToItem;
import io.annot8.core.data.BaseItem;
import io.annot8.core.data.Item;

@ExtendWith(MockitoExtension.class)
class SimpleItemQueueReaderTest {

  @Mock BaseItemQueue baseItemQueue;

  @Mock BaseItemToItem converter;

  @Test
  void hasItems() {

    when(baseItemQueue.hasItems()).thenReturn(true);
    SimpleItemQueueReader reader = new SimpleItemQueueReader(baseItemQueue, converter);

    assertThat(reader.hasItems()).isTrue();

    verify(baseItemQueue).hasItems();
  }

  @Test
  void next() {
    BaseItem bi = mock(BaseItem.class);
    Item i = mock(Item.class);

    when(baseItemQueue.next()).thenReturn(bi);
    when(converter.convert(bi)).thenReturn(i);

    SimpleItemQueueReader reader = new SimpleItemQueueReader(baseItemQueue, converter);

    assertThat(reader.next()).isEqualTo(i);

    verify(baseItemQueue).next();
  }
}
