/* Annot8 (annot8.io) - Licensed under Apache-2.0. */
package io.annot8.core.filters;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

  Filter<String> f =
          s -> s.length() == 1;

  Filter<String> u =
          s -> s.startsWith("t");

  @Test
  void test() {
    assertTrue(f.test("1"));
    assertFalse(f.test("12"));

    assertTrue(u.test("t"));
    assertFalse(u.test("f"));
  }

  @Test
  void negate() {
    Filter<String> n = f.negate();
    assertFalse(n.test("1"));
    assertTrue(n.test("12"));

    NotFilter<String> nf = (NotFilter<String>) n;
    assertEquals(f, nf.getFilter());
  }

  @Test
  void and() {
    Filter<String> n = f.and(u);

    assertFalse(n.test("1"));
    assertFalse(n.test("tt"));
    assertFalse(n.test("f"));
    assertTrue(n.test("t"));

    AndFilter<String> af = (AndFilter<String>) n;
    assertThat(af.getFilters()).containsExactly(f, u);
  }

  @Test
  void or() {
    Filter<String> n = f.or(u);

    assertTrue(n.test("1"));
    assertTrue(n.test("tt"));
    assertTrue(n.test("f"));
    assertTrue(n.test("t"));
    assertFalse(n.test("ff"));
    assertFalse(n.test("11"));

    OrFilter<String> of = (OrFilter<String>) n;
    assertThat(of.getFilters()).containsExactly(f, u);
  }
}
