package org.d3if3084.nebula.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3084.nebula.R
import org.d3if3084.nebula.databinding.FragmentFindzodiacBinding
import org.d3if3084.nebula.model.ZodiacSign
import org.d3if3084.nebula.model.ZodiacType

class FindZodiacFragment : Fragment() {
    private lateinit var binding: FragmentFindzodiacBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFindzodiacBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchBtn.setOnClickListener { findZodiac() }
        binding.detailsButton.setOnClickListener { viewModel.startNavigation() }
        binding.switchNightMode.setOnClickListener { toggleTheme() }
        binding.shareButton.setOnClickListener { shareData() }
        binding.buttonGroup.visibility = View.VISIBLE
        viewModel.getZodiacResults().observe(requireActivity()){
            showResult(it)
        }
        viewModel.getNavigation().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            findNavController().navigate(FindZodiacFragmentDirections
                .actionFindZodiacFragmentToDetailsFragment(it))
            viewModel.finishNavigation()
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_findZodiacFragment_to_aboutFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareData() {
        val message = getString(R.string.share_template,
            binding.dateInp.text,
            binding.monthInp.text,
            binding.yearInp.text,
            binding.zodiacResults.text,
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
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
            Toast.makeText(context, R.string.date_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val month = binding.monthInp.text.toString()
        if (TextUtils.isEmpty(month)) {
            Toast.makeText(context, R.string.month_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val year = binding.yearInp.text.toString()
        if (TextUtils.isEmpty(year)) {
            Toast.makeText(context, R.string.year_invalid, Toast.LENGTH_LONG).show()
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