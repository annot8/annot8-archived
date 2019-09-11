open module io.annot8.common.pipelines {
  requires com.google.common;
  requires transitive io.annot8.core;
  requires org.slf4j;
  requires io.annot8.common.implementations;
  requires io.annot8.common.utils;

  exports io.annot8.common.pipelines;
  exports io.annot8.common.pipelines.impl;
}
