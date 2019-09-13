open module io.annot8.testing.tck.impl {
  requires io.annot8.common.implementations;
  requires io.annot8.common.data;
  requires io.annot8.testing.testimpl;
  requires org.junit.jupiter.api;
  requires org.mockito;

    exports io.annot8.testing.tck.impl;
}
