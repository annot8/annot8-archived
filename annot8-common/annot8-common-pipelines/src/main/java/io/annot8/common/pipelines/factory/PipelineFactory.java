/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.pipelines.factory;

import io.annot8.common.pipelines.elements.Pipeline;
import io.annot8.common.pipelines.factory.configuration.PipelineConfiguration;
import io.annot8.core.exceptions.IncompleteException;

public interface PipelineFactory {

  Pipeline create(PipelineConfiguration configuration) throws IncompleteException;
}
