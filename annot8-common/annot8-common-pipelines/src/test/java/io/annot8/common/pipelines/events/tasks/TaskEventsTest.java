/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.tasks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.annot8.common.pipelines.elements.Task;
import io.annot8.common.pipelines.events.TaskEvent;

class TaskEventsTest {

  @ParameterizedTest
  @ValueSource(classes = {TaskBegunEvent.class, TaskCompleteEvent.class, TaskErrorEvent.class})
  void testNew(Class<TaskEvent> clazz)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    final Task task = mock(Task.class);
    final TaskEvent event = clazz.getConstructor(Task.class).newInstance(task);
    assertThat(event.getTask()).isEqualTo(task);
  }
}
