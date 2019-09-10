/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.data.Item;

@ExtendWith(MockitoExtension.class)
class SimpleItemQueueReaderTest {

  @Mock
  ItemQueue itemQueue;

  @Test
  void hasItems() {

    when(itemQueue.hasItems()).thenReturn(true);
    SimpleItemQueueReader reader = new SimpleItemQueueReader(itemQueue);

    assertThat(reader.hasItems()).isTrue();

    verify(itemQueue).hasItems();
  }

  @Test
  void next() {
    Item bi = mock(Item.class);

    when(itemQueue.next()).thenReturn(bi);

    SimpleItemQueueReader reader = new SimpleItemQueueReader(itemQueue);

    assertThat(reader.next()).isEqualTo(bi);

    verify(itemQueue).next();
  }
}
