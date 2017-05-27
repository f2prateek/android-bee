package com.f2prateek.bee

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class BeeTest {
    @Test fun testCharacters() {
        assertThat("abcdefghijklmnopqrstuvwxyz".spell()) //
                .isEqualTo("Alfa Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo " +
                        "Lima Mike November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor " +
                        "Whiskey X-ray Yankee Zulu")
    }

    @Test fun testCharactersUppercase() {
        assertThat("ABCDEFGHIJKLMNOPQRSTUVWXYZ".spell())
                .isEqualTo("Alfa Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo " +
                        "Lima Mike November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor " +
                        "Whiskey X-ray Yankee Zulu")
    }

    @Test fun testDigits() {
        assertThat("1234567890".spell())
                .isEqualTo("One Two Three Four Five Six Seven Eight Nine Zero")
    }

    @Test fun testUnknown() {
        assertThat(",./".spell()).isEqualTo(", . /")
    }

    @Test fun testSpace() {
        assertThat("a b c".spell()).isEqualTo("Alfa\nBravo\nCharlie")
    }
}
