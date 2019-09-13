open module io.annot8.common.data {
  requires transitive io.annot8.api;
  requires io.annot8.common.utils;
  requires java.json.bind;

  exports io.annot8.common.data.bounds;
  exports io.annot8.common.data.content;
  exports io.annot8.common.data.tuple;
  exports io.annot8.common.data.properties;
}
