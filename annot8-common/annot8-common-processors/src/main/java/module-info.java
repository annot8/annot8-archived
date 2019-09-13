open module io.annot8.common.processors {
  requires transitive io.annot8.api;
  requires com.google.common;
  requires io.annot8.common.data;

  exports io.annot8.common.processors.filters;
  exports io.annot8.common.processors.indices;
}
