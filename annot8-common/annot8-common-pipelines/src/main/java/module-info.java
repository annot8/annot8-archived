open module io.annot8.common.pipelines {
    requires transitive io.annot8.core;
  requires org.slf4j;
  requires io.annot8.common.implementations;
    requires io.annot8.common.components;

  exports io.annot8.common.pipelines;
  exports io.annot8.common.pipelines.impl;
}
