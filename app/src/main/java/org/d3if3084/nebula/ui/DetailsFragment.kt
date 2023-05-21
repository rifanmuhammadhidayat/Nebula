package org.d3if3084.nebula.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3084.nebula.R
import org.d3if3084.nebula.databinding.FragmentDetailsBinding
import org.d3if3084.nebula.model.ZodiacSign

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    private val args: DetailsFragmentArgs by navArgs()

    private fun updateUI(zodiac : ZodiacSign){
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (zodiac) {
            ZodiacSign.AQUARIUS -> {
                actionBar?.title = getString(R.string.title_aquarius)
                binding.imageView.setImageResource(R.drawable.aquariusd)
                binding.textView.text = getString(R.string.details_aquarius)
            }
            ZodiacSign.PISCES -> {
                actionBar?.title = getString(R.string.title_pisces)
                binding.imageView.setImageResource(R.drawable.piscesd)
                binding.textView.text = getString(R.string.details_pisces)
            }
            ZodiacSign.ARIES -> {
                actionBar?.title = getString(R.string.title_aries)
                binding.imageView.setImageResource(R.drawable.ariesd)
                binding.textView.text = getString(R.string.details_aries)
            }
            ZodiacSign.TAURUS -> {
                actionBar?.title = getString(R.string.title_taurus)
                binding.imageView.setImageResource(R.drawable.taurusd)
                binding.textView.text = getString(R.string.details_taurus)
            }
            ZodiacSign.GEMINI -> {
                actionBar?.title = getString(R.string.title_gemini)
                binding.imageView.setImageResource(R.drawable.geminid)
                binding.textView.text = getString(R.string.details_gemini)
            }
            ZodiacSign.CANCER -> {
                actionBar?.title = getString(R.string.title_cancer)
                binding.imageView.setImageResource(R.drawable.cancerd)
                binding.textView.text = getString(R.string.details_cancer)
            }
            ZodiacSign.LEO -> {
                actionBar?.title = getString(R.string.title_leo)
                binding.imageView.setImageResource(R.drawable.leod)
                binding.textView.text = getString(R.string.details_leo)
            }
            ZodiacSign.VIRGO -> {
                actionBar?.title = getString(R.string.title_virgo)
                binding.imageView.setImageResource(R.drawable.virgod)
                binding.textView.text = getString(R.string.details_virgo)
            }
            ZodiacSign.LIBRA -> {
                actionBar?.title = getString(R.string.title_libra)
                binding.imageView.setImageResource(R.drawable.librad)
                binding.textView.text = getString(R.string.details_libra)
            }
            ZodiacSign.SCORPIO -> {
                actionBar?.title = getString(R.string.title_scorpio)
                binding.imageView.setImageResource(R.drawable.scorpiod)
                binding.textView.text = getString(R.string.details_scorpio)
            }
            ZodiacSign.SAGITTARIUS -> {
                actionBar?.title = getString(R.string.title_sagittarius)
                binding.imageView.setImageResource(R.drawable.sagitariusd)
                binding.textView.text = getString(R.string.details_sagittarius)
            }
            ZodiacSign.CAPRICORN -> {
                actionBar?.title = getString(R.string.title_capricorn)
                binding.imageView.setImageResource(R.drawable.capricornd)
                binding.textView.text = getString(R.string.details_capricorn)
            }
            ZodiacSign.INVALID -> {
                actionBar?.title = getString(R.string.title_invalid)
                binding.textView.text = getString(R.string.details_invalid)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateUI(args.zodiac)
    }
}