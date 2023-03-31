package org.d3if3084.nebula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3084.nebula.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener { findZodiac() }
    }

    private fun findZodiac() {

        val date = binding.dateInp.text.toString()
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, R.string.date_invalid, Toast.LENGTH_LONG).show()
            return
        }
        try {
            date.toInt()
        } catch (e: java.lang.NumberFormatException) {
            Toast.makeText(this, "Input must be a number", Toast.LENGTH_LONG).show()
            return
        }

        val month = binding.monthInp.text.toString()
        if (TextUtils.isEmpty(month)) {
            Toast.makeText(this, R.string.month_invalid, Toast.LENGTH_LONG).show()
            return
        }
        try {
            month.toInt()
        } catch (e: java.lang.NumberFormatException) {
            Toast.makeText(this, "Input must be a number", Toast.LENGTH_LONG).show()
            return
        }

        val year = binding.yearInp.text.toString()
        if (TextUtils.isEmpty(year)) {
            Toast.makeText(this, R.string.year_invalid, Toast.LENGTH_LONG).show()
            return
        }
        try {
            year.toInt()
        } catch (e: java.lang.NumberFormatException) {
            Toast.makeText(this, "Input must be a number", Toast.LENGTH_LONG).show()
            return
        }

        val zodiac = getZodiacSign(date.toInt(), month.toInt())
        binding.zodiacResults.text = getString(R.string.zodiac_results_x, zodiac)
    }
    private fun getZodiacSign(date: Int, month: Int): String {
        val imageView = binding.iconZodiac
        val imgNumber: Int
        val result: String = when (month) {
            1 -> {
                if (date <= 19) {
                    imgNumber = R.drawable.capricorn
                    "Capricorn: Male Goat\n" +
                            "Element: Earth\n" +
                            "Ruler: Saturn\n" +
                            "Best Compatibility: Taurus, Cancer\n"
                } else {
                    imgNumber = R.drawable.aquarius
                    "Aquarius: Water Bearer\n" +
                            "Element: Air\n" +
                            "Ruler: Uranus\n" +
                            "Best Compatibility: Leo, Sagittarius\n"
                }
            }
            2 -> {
                if (date <= 18) {
                    imgNumber = R.drawable.aquarius
                    "Aquarius: Water Bearer\n" +
                            "Element: Air\n" +
                            "Ruler: Uranus\n" +
                            "Best Compatibility: Leo, Sagittarius\n"
                } else {
                    imgNumber = R.drawable.pisces
                    "Pisces: Fish\n" +
                            "Element: Water\n" +
                            "Ruler: Neptune\n" +
                            "Best Compatibility: Virgo, Taurus\n"
                }
            }
            3 -> {
                if (date <= 20) {
                    imgNumber = R.drawable.pisces
                    "Pisces: Fish\n" +
                            "Element: Water\n" +
                            "Ruler: Neptune\n" +
                            "Best Compatibility: Virgo, Taurus\n"
                } else {
                    imgNumber = R.drawable.aries
                    "Aries: Ram\n" +
                            "Element: Fire\n" +
                            "Ruler: Mars\n" +
                            "Best Compatibility: Libra, Leo\n"
                }
            }
            4 -> {
                if (date <= 19) {
                    imgNumber = R.drawable.aries
                    "Aries: Ram\n" +
                            "Element: Fire\n" +
                            "Ruler: Mars\n" +
                            "Best Compatibility: Libra, Leo\n"
                } else {
                    imgNumber = R.drawable.taurus
                    "Taurus: Bull\n" +
                            "Element: Earth\n" +
                            "Ruler: Venus\n" +
                            "Best Compatibility: Cancer, Scorpio\n"
                }
            }
            5 -> {
                if (date <= 20) {
                    imgNumber = R.drawable.taurus
                    "Taurus: Bull\n" +
                            "Element: Earth\n" +
                            "Ruler: Venus\n" +
                            "Best Compatibility: Cancer, Scorpio\n"
                } else {
                    imgNumber = R.drawable.gemini
                    "Gemini: The Twins\n" +
                            "Element: Air\n" +
                            "Ruler: Mercury\n" +
                            "Greatest Compatibility: Sagittarius, Aquarius\n"
                }
            }
            6 -> {
                if (date <= 20) {
                    imgNumber = R.drawable.gemini
                    "Gemini: The Twins\n" +
                            "Element: Air\n" +
                            "Ruler: Mercury\n" +
                            "Greatest Compatibility: Sagittarius, Aquarius\n"
                } else {
                    imgNumber = R.drawable.cancer
                    "Cancer: Crab\n" +
                            "Element: Water\n" +
                            "Ruler: Moon\n" +
                            "Best Compatibility: Capricorn, Taurus\n"
                }
            }
            7 -> {
                if (date <= 22) {
                    imgNumber = R.drawable.cancer
                    "Cancer: Crab\n" +
                            "Element: Water\n" +
                            "Ruler: Moon\n" +
                            "Best Compatibility: Capricorn, Taurus\n"
                } else {
                    imgNumber = R.drawable.leo
                    "Leo: Lion\n" +
                            "Element: Fire\n" +
                            "Ruler: Sun\n" +
                            "Best Compatibility: Aquarius, Gemini\n"
                }
            }
            8 -> {
                if (date <= 22) {
                    imgNumber = R.drawable.leo
                    "Leo: Lion\n" +
                            "Element: Fire\n" +
                            "Ruler: Sun\n" +
                            "Best Compatibility: Aquarius, Gemini\n"
                } else {
                    imgNumber = R.drawable.virgo
                    "Virgo: The Girl\n" +
                            "Element: Earth\n" +
                            "Ruler: Mercury\n" +
                            "Best Compatibility: Pisces, Cancer\n"
                }
            }
            9 -> {
                if (date <= 22) {
                    imgNumber = R.drawable.virgo
                    "Virgo: The Girl\n" +
                            "Element: Earth\n" +
                            "Ruler: Mercury\n" +
                            "Best Compatibility: Pisces, Cancer\n"
                } else {
                    imgNumber = R.drawable.libra
                    "Libra: Scales\n" +
                            "Element: Air\n" +
                            "Ruler: Venus\n" +
                            "Greatest Compatibility: Aries, Sagittarius\n"
                }
            }
            10 -> {
                if (date <= 22) {
                    imgNumber = R.drawable.libra
                    "Libra: Scales\n" +
                            "Element: Air\n" +
                            "Ruler: Venus\n" +
                            "Greatest Compatibility: Aries, Sagittarius\n"
                } else {
                    imgNumber = R.drawable.scorpio
                    "Scorpio: Scorpion\n" +
                            "Element: Water\n" +
                            "Ruler: Pluto\n" +
                            "Best Compatibility: Taurus, Cancer\n"
                }
            }
            11 -> {
                if (date <= 21) {
                    imgNumber = R.drawable.scorpio
                    "Scorpio: Scorpion\n" +
                            "Element: Water\n" +
                            "Ruler: Pluto\n" +
                            "Best Compatibility: Taurus, Cancer\n"
                } else {
                    imgNumber = R.drawable.sagittarius
                    "Sagittarius: The Archer\n" +
                            "Element: Fire\n" +
                            "Ruler: Jupiter\n" +
                            "Best Compatibility: Gemini, Aries\n"
                }
            }
            12 -> {
                if (date <= 21) {
                    imgNumber = R.drawable.sagittarius
                    "Sagittarius: The Archer\n" +
                            "Element: Fire\n" +
                            "Ruler: Jupiter\n" +
                            "Best Compatibility: Gemini, Aries\n"
                } else {
                    imgNumber = R.drawable.capricorn
                    "Capricorn: Male Goat\n" +
                            "Element: Earth\n" +
                            "Ruler: Saturn\n" +
                            "Best Compatibility: Taurus, Cancer\n"
                }
            }
            else -> {
                binding.iconZodiac.isEnabled = false
                return "Invalid date"
            }
        }
        imageView.setImageResource(imgNumber)
        return result
    }
}