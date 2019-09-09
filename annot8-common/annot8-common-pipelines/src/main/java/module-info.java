open module io.annot8.common.pipelines {
  requires com.google.common;
  requires transitive io.annot8.core;
  requires org.slf4j;
  requires io.annot8.common.implementations;
  requires io.annot8.common.utils;

  exports io.annot8.common.pipelines.events.pipe;
  exports io.annot8.common.pipelines.events.source;
  exports io.annot8.common.pipelines.events.tasks;
  exports io.annot8.common.pipelines.events.jobs;
  exports io.annot8.common.pipelines.factory;
  exports io.annot8.common.pipelines.factory.configuration;
  exports io.annot8.common.pipelines.base;
  exports io.annot8.common.pipelines.elements;
  exports io.annot8.common.pipelines.listeners;
  exports io.annot8.common.pipelines.queues;
  exports io.annot8.common.pipelines.simple;
  exports io.annot8.common.pipelines.definitions;
  exports io.annot8.common.pipelines.delegates;
  exports io.annot8.common.pipelines.events;
}
