/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.core.data.Item;

@ExtendWith(MockitoExtension.class)
class MemoryItemQueueTest {

  @Mock Item a;

  @Mock Item b;

  @Mock Item c;

  MemoryItemQueue queue = new MemoryItemQueue();

  @Test
  void retainOrder() {
    queue.add(a);
    queue.add(b);
    queue.add(c);

    assertThat(queue.hasItems()).isTrue();
    assertThat(queue.next()).isEqualTo(a);

    assertThat(queue.hasItems()).isTrue();
    assertThat(queue.next()).isEqualTo(b);

    assertThat(queue.hasItems()).isTrue();
    assertThat(queue.next()).isEqualTo(c);

    assertThat(queue.hasItems()).isFalse();
  }

  @Test
  void emptyHasNoItems() {
    assertThat(queue.hasItems()).isFalse();
  }
}
