package io.annot8.core.helpers;

import io.annot8.core.filters.Filter;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WithFilterTest {

    @Test
    void find() {
        WithFilter<String> f = filter -> Stream.of("1", "12", "3").filter(filter::test);

        assertThat(f.find(s -> s.length() > 1)).contains("12");
    }
}