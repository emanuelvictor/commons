package io.github.emanuelvictor.commons.normalizer;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.github.emanuelvictor.commons.normalizer.Normalizer.normalize;

/**
 *
 */
class NormalizerTests {

    /**
     *
     */
    @Test
    void removingSpecialCharactersTest() {
        Assertions.assertThat(normalize("teste\n\t\r")).isEqualTo("teste   ");
    }

    /**
     *
     */
    @Test
    void removingSpecialCharactersAndTrimTest() {
        Assertions.assertThat(normalize("teste\n\t\r", true)).isEqualTo("teste");
    }

    /**
     *
     */
    @Test
    void normalizeFunctionWithNullInputTest() {
        Assertions.assertThat(normalize(null)).isNull();
    }

}


