open module io.annot8.common.support {
  requires transitive io.annot8.core;
  requires com.google.common;
  requires io.annot8.common.data;

  exports io.annot8.common.support.indices;
  exports io.annot8.common.support.filters;
}
