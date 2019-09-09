/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.source;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.annot8.common.pipelines.events.SourceEvent;
import io.annot8.core.components.Source;

class SourceEventsTest {

  @ParameterizedTest
  @ValueSource(
    classes = {
      SourceDoneEvent.class,
      SourceEmptyEvent.class,
      SourceErrorEvent.class,
      SourceReadEvent.class
    }
  )
  void testNew(Class<SourceEvent> clazz)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    final Source source = mock(Source.class);
    final SourceEvent event = clazz.getConstructor(Source.class).newInstance(source);
    assertThat(event.getSource()).isEqualTo(source);
  }
}
