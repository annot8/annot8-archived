/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.common.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.annot8.common.components.metering.Metrics;
import io.annot8.common.components.metering.NoOpMetrics;
import io.annot8.core.components.Annot8Component;
import io.micrometer.core.lang.Nullable;

public abstract class AbstractComponent implements Annot8Component {

  private Logger logger;

  private Metrics metrics;

  /**
   * Get the (slf4j) logger for this component.
   *
   * <p>Ensure you have called configure (ie super.configure()) before using this. Otherwise you
   * will be given a no-op logger.
   *
   * @return non-null logger
   */
  protected Logger log() {
    // if configure has not been called we might not have a logger, so check and create is necessary
    if (logger == null) {
      createDefaultLogger();
    }

    return logger;
  }

  /**
   * Get the metrics for this component
   *
   * <p>Ensure you have called configure (ie super.configure()) before using this. Otherwise you
   * will be given a no-op logger.
   *
   * @return non-null metrics
   */
  protected Metrics metrics() {
    // if configure has not been called we might not have a metrics, so check and create is
    // necessary
    if (metrics == null) {
      createDefaultMetrics();
    }

    return metrics;
  }

  /**
   * Called by external manager (eg a pipeline) to set up logging
   *
   * @param logger logger to use, or null to change to no-op logging
   */
  public void setLogger(@Nullable Logger logger) {
    this.logger = logger;
  }

  /**
   * Called by external manager (eg a pipeline) to set up metrics
   *
   * @param metrics to use, or null to change to no-op metrics
   */
  public void setMetrics(Metrics metrics) {
    this.metrics = metrics;
  }

  private void createDefaultLogger() {
    logger = LoggerFactory.getLogger(this.getClass());
  }

  private void createDefaultMetrics() {
    metrics = NoOpMetrics.instance();
  }
}
