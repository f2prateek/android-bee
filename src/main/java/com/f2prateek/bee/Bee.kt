package com.f2prateek.bee

fun CharSequence.spell(): String {
    val sb = StringBuilder()

    var addSpace = true
    for (c in this) {
        if (Character.isSpaceChar(c)) {
            if (sb.isNotEmpty()) {
                sb.append('\n')
                addSpace = false
            }
            continue
        }

        if (sb.isNotEmpty() && addSpace) {
            sb.append(' ')
        }
        addSpace = true

        val spelt = c.spell()
        if (spelt == null) {
            sb.append(c)
        } else {
            sb.append(spelt)
        }
    }

    return sb.toString()
}

fun Char.spell(): String? {
    val c = Character.toLowerCase(this)
    when (c) {
        'a' -> return "Alfa"
        'b' -> return "Bravo"
        'c' -> return "Charlie"
        'd' -> return "Delta"
        'e' -> return "Echo"
        'f' -> return "Foxtrot"
        'g' -> return "Golf"
        'h' -> return "Hotel"
        'i' -> return "India"
        'j' -> return "Juliett"
        'k' -> return "Kilo"
        'l' -> return "Lima"
        'm' -> return "Mike"
        'n' -> return "November"
        'o' -> return "Oscar"
        'p' -> return "Papa"
        'q' -> return "Quebec"
        'r' -> return "Romeo"
        's' -> return "Sierra"
        't' -> return "Tango"
        'u' -> return "Uniform"
        'v' -> return "Victor"
        'w' -> return "Whiskey"
        'x' -> return "X-ray"
        'y' -> return "Yankee"
        'z' -> return "Zulu"
        '0' -> return "Zero"
        '1' -> return "One"
        '2' -> return "Two"
        '3' -> return "Three"
        '4' -> return "Four"
        '5' -> return "Five"
        '6' -> return "Six"
        '7' -> return "Seven"
        '8' -> return "Eight"
        '9' -> return "Nine"
        else -> return null
    }
}
