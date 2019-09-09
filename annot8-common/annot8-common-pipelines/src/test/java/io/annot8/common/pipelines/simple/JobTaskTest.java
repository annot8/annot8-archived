/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.simple;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.annot8.common.pipelines.elements.Job;

@ExtendWith(MockitoExtension.class)
class JobTaskTest {

  @Mock Job job;

  @Test
  void perform() {
    final JobTask task = new JobTask(job);
    task.run();

    verify(job).run();
  }
}
