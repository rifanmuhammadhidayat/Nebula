package org.d3if3084.nebula.model

import org.d3if3084.nebula.db.ZodiacEntity

fun ZodiacEntity.getZodiacSign() : ZodiacType {
    val zodiacType = ZodiacType(ZodiacSign.INVALID, "")
    when (month) {
        1 -> {
            if (date <= 19) {
                zodiacType.zodiacSign = ZodiacSign.CAPRICORN
                zodiacType.resultZT = "Capricorn: Male Goat\n" +
                        "Element: Earth\n" +
                        "Ruler: Saturn\n" +
                        "Best Compatibility: Taurus, Cancer\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.AQUARIUS
                zodiacType.resultZT = "Aquarius: Water Bearer\n" +
                        "Element: Air\n" +
                        "Ruler: Uranus\n" +
                        "Best Compatibility: Leo, Sagittarius\n"
            }
        }
        2 -> {
            if (date <= 18) {
                zodiacType.zodiacSign = ZodiacSign.AQUARIUS
                zodiacType.resultZT = "Aquarius: Water Bearer\n" +
                        "Element: Air\n" +
                        "Ruler: Uranus\n" +
                        "Best Compatibility: Leo, Sagittarius\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.PISCES
                zodiacType.resultZT = "Pisces: Fish\n" +
                        "Element: Water\n" +
                        "Ruler: Neptune\n" +
                        "Best Compatibility: Virgo, Taurus\n"
            }
        }
        3 -> {
            if (date <= 20) {
                zodiacType.zodiacSign = ZodiacSign.PISCES
                zodiacType.resultZT = "Pisces: Fish\n" +
                        "Element: Water\n" +
                        "Ruler: Neptune\n" +
                        "Best Compatibility: Virgo, Taurus\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.ARIES
                zodiacType.resultZT = "Aries: Ram\n" +
                        "Element: Fire\n" +
                        "Ruler: Mars\n" +
                        "Best Compatibility: Libra, Leo\n"
            }
        }
        4 -> {
            if (date <= 19) {
                zodiacType.zodiacSign = ZodiacSign.ARIES
                zodiacType.resultZT = "Aries: Ram\n" +
                        "Element: Fire\n" +
                        "Ruler: Mars\n" +
                        "Best Compatibility: Libra, Leo\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.TAURUS
                zodiacType.resultZT = "Taurus: Bull\n" +
                        "Element: Earth\n" +
                        "Ruler: Venus\n" +
                        "Best Compatibility: Cancer, Scorpio\n"
            }
        }
        5 -> {
            if (date <= 20) {
                zodiacType.zodiacSign = ZodiacSign.TAURUS
                zodiacType.resultZT = "Taurus: Bull\n" +
                        "Element: Earth\n" +
                        "Ruler: Venus\n" +
                        "Best Compatibility: Cancer, Scorpio\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.GEMINI
                zodiacType.resultZT = "Gemini: The Twins\n" +
                        "Element: Air\n" +
                        "Ruler: Mercury\n" +
                        "Greatest Compatibility: Sagittarius, Aquarius\n"
            }
        }
        6 -> {
            if (date <= 20) {
                zodiacType.zodiacSign = ZodiacSign.GEMINI
                zodiacType.resultZT = "Gemini: The Twins\n" +
                        "Element: Air\n" +
                        "Ruler: Mercury\n" +
                        "Greatest Compatibility: Sagittarius, Aquarius\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.CANCER
                zodiacType.resultZT = "Cancer: Crab\n" +
                        "Element: Water\n" +
                        "Ruler: Moon\n" +
                        "Best Compatibility: Capricorn, Taurus\n"
            }
        }
        7 -> {
            if (date <= 22) {
                zodiacType.zodiacSign = ZodiacSign.CANCER
                zodiacType.resultZT = "Cancer: Crab\n" +
                        "Element: Water\n" +
                        "Ruler: Moon\n" +
                        "Best Compatibility: Capricorn, Taurus\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.LEO
                zodiacType.resultZT = "Leo: Lion\n" +
                        "Element: Fire\n" +
                        "Ruler: Sun\n" +
                        "Best Compatibility: Aquarius, Gemini\n"
            }
        }
        8 -> {
            if (date <= 22) {
                zodiacType.zodiacSign = ZodiacSign.LEO
                zodiacType.resultZT = "Leo: Lion\n" +
                        "Element: Fire\n" +
                        "Ruler: Sun\n" +
                        "Best Compatibility: Aquarius, Gemini\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.VIRGO
                zodiacType.resultZT = "Virgo: The Girl\n" +
                        "Element: Earth\n" +
                        "Ruler: Mercury\n" +
                        "Best Compatibility: Pisces, Cancer\n"
            }
        }
        9 -> {
            if (date <= 22) {
                zodiacType.zodiacSign = ZodiacSign.VIRGO
                zodiacType.resultZT = "Virgo: The Girl\n" +
                        "Element: Earth\n" +
                        "Ruler: Mercury\n" +
                        "Best Compatibility: Pisces, Cancer\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.LIBRA
                zodiacType.resultZT = "Libra: Scales\n" +
                        "Element: Air\n" +
                        "Ruler: Venus\n" +
                        "Greatest Compatibility: Aries, Sagittarius\n"
            }
        }
        10 -> {
            if (date <= 22) {
                zodiacType.zodiacSign = ZodiacSign.LIBRA
                zodiacType.resultZT = "Libra: Scales\n" +
                        "Element: Air\n" +
                        "Ruler: Venus\n" +
                        "Greatest Compatibility: Aries, Sagittarius\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.SCORPIO
                zodiacType.resultZT = "Scorpio: Scorpion\n" +
                        "Element: Water\n" +
                        "Ruler: Pluto\n" +
                        "Best Compatibility: Taurus, Cancer\n"
            }
        }
        11 -> {
            if (date <= 21) {
                zodiacType.zodiacSign = ZodiacSign.SCORPIO
                zodiacType.resultZT = "Scorpio: Scorpion\n" +
                        "Element: Water\n" +
                        "Ruler: Pluto\n" +
                        "Best Compatibility: Taurus, Cancer\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.SAGITTARIUS
                zodiacType.resultZT = "Sagittarius: The Archer\n" +
                        "Element: Fire\n" +
                        "Ruler: Jupiter\n" +
                        "Best Compatibility: Gemini, Aries\n"
            }
        }
        12 -> {
            if (date <= 21) {
                zodiacType.zodiacSign = ZodiacSign.SAGITTARIUS
                zodiacType.resultZT = "Sagittarius: The Archer\n" +
                        "Element: Fire\n" +
                        "Ruler: Jupiter\n" +
                        "Best Compatibility: Gemini, Aries\n"
            } else {
                zodiacType.zodiacSign = ZodiacSign.CAPRICORN
                zodiacType.resultZT = "Capricorn: Male Goat\n" +
                        "Element: Earth\n" +
                        "Ruler: Saturn\n" +
                        "Best Compatibility: Taurus, Cancer\n"
            }
        }
        else -> {
            zodiacType.zodiacSign = ZodiacSign.INVALID
            zodiacType.resultZT = "Invalid Date"
        }
    }
    return zodiacType
}