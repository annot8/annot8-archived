open module io.annot8.common.support {
  requires transitive io.annot8.api;
  requires com.google.common;
  requires io.annot8.common.data;

  exports io.annot8.common.processor.filters;
  exports io.annot8.common.processor.indices;
}
