package com.f2prateek.bee;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class BeeTest {
  @Test public void testCharacters() {
    assertThat(Bee.spellReal("abcdefghijklmnopqrstuvwxyz")) //
        .isEqualTo("Alfa Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike "
            + "November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee "
            + "Zulu");
  }

  @Test public void testCharactersUppercase() {
    assertThat(Bee.spellReal("ABCDEFGHIJKLMNOPQRSTUVWXYZ")) //
        .isEqualTo("Alfa Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike "
            + "November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee "
            + "Zulu");
  }

  @Test public void testDigits() {
    assertThat(Bee.spellReal("1234567890")) //
        .isEqualTo("One Two Three Four Five Six Seven Eight Nine Zero");
  }

  @Test public void testUnknown() {
    assertThat(Bee.spellReal(",./")).isEqualTo(", . /");
  }

  @Test public void testSpace() {
    assertThat(Bee.spellReal("a b c")).isEqualTo("Alfa\nBravo\nCharlie");
  }
}
