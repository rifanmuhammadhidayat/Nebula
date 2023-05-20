package org.d3if3084.nebula

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import org.d3if3084.nebula.databinding.ActivityMainBinding
import org.d3if3084.nebula.model.ZodiacSign
import org.d3if3084.nebula.model.ZodiacType

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener { findZodiac() }
        binding.switchNightMode.setOnClickListener { toggleTheme() }

        viewModel.getZodiacResults().observe(this){
            showResult(it)
        }
    }

    private fun toggleTheme() {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            binding.switchNightMode.setTextColor(Color.BLACK)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            binding.switchNightMode.setTextColor(Color.WHITE)
        }
    }

     private fun findZodiac() {
        val date = binding.dateInp.text.toString()
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, R.string.date_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val month = binding.monthInp.text.toString()
        if (TextUtils.isEmpty(month)) {
            Toast.makeText(this, R.string.month_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val year = binding.yearInp.text.toString()
        if (TextUtils.isEmpty(year)) {
            Toast.makeText(this, R.string.year_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val zodiac = viewModel.getZodiacSign(date.toInt(), month.toInt())
         viewModel.setZodiacResults(zodiac.zodiacSign, zodiac.resultZT)
    }

    private fun showResult(zodiac: ZodiacType?){
        if (zodiac == null) return
        //set image
        val imageView = binding.iconZodiac
        imageView.setImageResource(getZodiacSignLabel(zodiac.zodiacSign))
        // set text
        binding.zodiacResults.text = getString(R.string.zodiac_results_x, zodiac.resultZT)
    }


    private fun getZodiacSignLabel(result: ZodiacSign): Int{
        val stringRes = when (result) {
            ZodiacSign.CAPRICORN -> R.drawable.capricorn
            ZodiacSign.AQUARIUS -> R.drawable.aquarius
            ZodiacSign.PISCES -> R.drawable.pisces
            ZodiacSign.ARIES -> R.drawable.aries
            ZodiacSign.TAURUS -> R.drawable.taurus
            ZodiacSign.GEMINI -> R.drawable.gemini
            ZodiacSign.CANCER -> R.drawable.cancer
            ZodiacSign.LEO -> R.drawable.leo
            ZodiacSign.VIRGO -> R.drawable.virgo
            ZodiacSign.LIBRA -> R.drawable.libra
            ZodiacSign.SCORPIO -> R.drawable.scorpio
            ZodiacSign.SAGITTARIUS -> R.drawable.sagittarius
            else -> {
                binding.iconZodiac.isEnabled = false
                return 0
            }
        }
        return stringRes
    }
}