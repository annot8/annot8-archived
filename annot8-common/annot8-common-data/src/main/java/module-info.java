open module io.annot8.common.data {
  requires transitive io.annot8.core;
  requires io.annot8.common.utils;
  requires java.json;
  requires java.json.bind;
  requires com.google.common;

  exports io.annot8.common.data.bounds;
  exports io.annot8.common.data.content;
  exports io.annot8.common.data.tuple;
}
