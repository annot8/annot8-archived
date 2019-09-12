open module io.annot8.common.components {
  requires transitive io.annot8.core;
  requires org.slf4j;
  requires micrometer.core;
  requires java.json.bind;

  exports io.annot8.common.components;
  exports io.annot8.common.components.logging;
  exports io.annot8.common.components.metering;
  exports io.annot8.common.components.capabilities;
}
