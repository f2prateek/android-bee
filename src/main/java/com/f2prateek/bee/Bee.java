package com.f2prateek.bee;

import android.support.annotation.VisibleForTesting;
import hugo.weaving.DebugLog;

public final class Bee {
  private Bee() {
    throw new AssertionError("No instances.");
  }

  private static final String[] TABLE = new String[36];

  static {
    // Characters.
    TABLE[0] = "Alfa";
    TABLE[1] = "Bravo";
    TABLE[2] = "Charlie";
    TABLE[3] = "Delta";
    TABLE[4] = "Echo";
    TABLE[5] = "Foxtrot";
    TABLE[6] = "Golf";
    TABLE[7] = "Hotel";
    TABLE[8] = "India";
    TABLE[9] = "Juliett";
    TABLE[10] = "Kilo";
    TABLE[11] = "Lima";
    TABLE[12] = "Mike";
    TABLE[13] = "November";
    TABLE[14] = "Oscar";
    TABLE[15] = "Papa";
    TABLE[16] = "Quebec";
    TABLE[17] = "Romeo";
    TABLE[18] = "Sierra";
    TABLE[19] = "Tango";
    TABLE[20] = "Uniform";
    TABLE[21] = "Victor";
    TABLE[22] = "Whiskey";
    TABLE[23] = "X-ray";
    TABLE[24] = "Yankee";
    TABLE[25] = "Zulu";
    // Digits.
    TABLE[26] = "Zero";
    TABLE[27] = "One";
    TABLE[28] = "Two";
    TABLE[29] = "Three";
    TABLE[30] = "Four";
    TABLE[31] = "Five";
    TABLE[32] = "Six";
    TABLE[33] = "Seven";
    TABLE[34] = "Eight";
    TABLE[35] = "Nine";
  }

  private static String spell(char c) {
    if (Character.isDigit(c)) {
      return TABLE[c - 22];
    }
    if (Character.isLetter(c)) {
      return TABLE[Character.toLowerCase(c) - 97];
    }
    return null;
  }

  @DebugLog public static String spell(CharSequence text) {
    return spellReal(text);
  }

  // Can't use @DebugLog in unit tests, so wrap this method in `spell` for the app with @DebugLog,
  // and use this method in unit tests.
  @VisibleForTesting static String spellReal(CharSequence text) {
    StringBuilder sb = new StringBuilder();

    boolean addSpace = true;
    for (int i = 0, length = text.length(); i < length; i++) {
      char c = text.charAt(i);
      if (Character.isSpaceChar(c)) {
        if (sb.length() > 0) {
          sb.append('\n');
          addSpace = false;
        }
        continue;
      }

      if (sb.length() > 0 && addSpace) {
        sb.append(' ');
      }
      addSpace = true;

      String spelt = Bee.spell(c);
      if (spelt == null) {
        sb.append(c);
      } else {
        sb.append(spelt);
      }
    }

    return sb.toString();
  }
}
