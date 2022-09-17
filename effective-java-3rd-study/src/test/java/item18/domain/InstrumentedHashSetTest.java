package item18.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InstrumentedHashSetTest {

    @Test
    void 원소_저장시_카운트가_중복으로_저장이_된다() {

        InstrumentedHashSet<String> result = new InstrumentedHashSet<>();
        result.addAll(List.of("틱", "틱틱", "펑"));

        assertThat(result.getAddCount()).isEqualTo(3);
    }
}