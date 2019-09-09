/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.elements.Job;
import io.annot8.common.pipelines.elements.Task;

@ExtendWith(MockitoExtension.class)
class SimpleJobTest {

  @Mock private Task task1;

  @Mock private Task task2;

  @Test
  void run() {

    final Job job = new SimpleJobBuilder().withName("test").withTask(task1).withTask(task2).build();

    assertThat(job.getName()).isEqualTo("test");
    assertThat(job.getId()).isNotBlank();

    job.run();

    verify(task1).run();
    verify(task2).run();
  }
}
