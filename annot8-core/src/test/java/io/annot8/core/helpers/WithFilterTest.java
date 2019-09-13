/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.helpers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class WithFilterTest {

  @Test
  void find() {
    WithFilter<String> f = filter -> Stream.of("1", "12", "3").filter(filter::test);

    assertThat(f.find(s -> s.length() > 1)).contains("12");
  }
}
