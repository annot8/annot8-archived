/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.elements;

public interface JobBuilder {

  JobBuilder withName(String name);

  JobBuilder withTask(Task task);

  Job build();
}
