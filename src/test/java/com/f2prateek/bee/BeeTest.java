package com.f2prateek.bee;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BeeTest {
  @Test public void testCharacters() {
    assertThat(Bee.spell("abcdefghijklmnopqrstuvwxyz")) //
        .isEqualTo("Alfa Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike "
            + "November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee "
            + "Zulu");
  }

  @Test public void testCharactersUppercase() {
    assertThat(Bee.spell("ABCDEFGHIJKLMNOPQRSTUVWXYZ")) //
        .isEqualTo("Alfa Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliett Kilo Lima Mike "
            + "November Oscar Papa Quebec Romeo Sierra Tango Uniform Victor Whiskey X-ray Yankee "
            + "Zulu");
  }

  @Test public void testDigits() {
    assertThat(Bee.spell("1234567890")) //
        .isEqualTo("One Two Three Four Five Six Seven Eight Nine Zero");
  }

  @Test public void testUnknown() {
    assertThat(Bee.spell(",./")).isEqualTo(", . /");
  }

  @Test public void testSpace() {
    assertThat(Bee.spell("a b c")).isEqualTo("Alfa\n Bravo\n Charlie");
  }
}
