module io.annot8.implementations.serialization {
  requires transitive io.annot8.api;
  requires transitive java.json.bind;
  requires transitive java.json;
  requires transitive org.slf4j;

  exports io.annot8.implementations.serialization;
}
