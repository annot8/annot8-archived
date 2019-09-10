/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.queues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QueuingSupportTest {

  @Mock BaseItemQueue queue;

  @Mock BaseItemFactory baseItemFactory;
  private QueuingSupport support;

  @BeforeEach
  void beforeEach() {
    support = new QueuingSupport(queue, baseItemFactory);
  }

  @Test
  void testFeeder() {
    support.getQueueFeeder();
  }

  void testReader() {
    support.getQueueReader();
  }

  void testBaseItemFactory() {
    support.getBaseItemFactory();
  }

  void testItemFactory() {

    support.getItemFactory();
  }

  void testConvertor() {
    support.getConverter();
  }
}
