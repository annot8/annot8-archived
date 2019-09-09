/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.events.jobs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.annot8.common.pipelines.elements.Job;
import io.annot8.common.pipelines.events.JobEvent;

class JobEventsTest {

  @ParameterizedTest
  @ValueSource(classes = {JobBegunEvent.class, JobCompleteEvent.class})
  void testNew(Class<JobEvent> clazz)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    final Job job = mock(Job.class);
    final JobEvent event = clazz.getConstructor(Job.class).newInstance(job);
    assertThat(event.getJob()).isEqualTo(job);
  }
}
