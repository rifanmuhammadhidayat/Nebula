package org.d3if3084.nebula.ui.history

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3084.nebula.R
import org.d3if3084.nebula.databinding.ItemHistoryBinding
import org.d3if3084.nebula.db.ZodiacDb
import org.d3if3084.nebula.db.ZodiacEntity
import org.d3if3084.nebula.model.ZodiacSign
import org.d3if3084.nebula.model.getZodiacSign
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter :
    ListAdapter<ZodiacEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ZodiacEntity>() {
                override fun areItemsTheSame(
                    oldData: ZodiacEntity, newData: ZodiacEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: ZodiacEntity, newData: ZodiacEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView)
    }

    class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: ZodiacEntity, view: View) = with(binding) {
            val zodiacResults = item.getZodiacSign()
            zodiacTextView.text = zodiacResults.zodiacSign.toString().substring(0, 1)
            val colorRes = when(zodiacResults.zodiacSign) {
                ZodiacSign.AQUARIUS -> R.color.aquarius
                ZodiacSign.PISCES -> R.color.pisces
                ZodiacSign.ARIES -> R.color.aries
                ZodiacSign.TAURUS -> R.color.taurus
                ZodiacSign.GEMINI -> R.color.gemini
                ZodiacSign.CANCER -> R.color.cancer
                ZodiacSign.LEO -> R.color.leo
                ZodiacSign.VIRGO -> R.color.virgo
                ZodiacSign.LIBRA -> R.color.libra
                ZodiacSign.SCORPIO -> R.color.scorpio
                ZodiacSign.SAGITTARIUS -> R.color.sagittarius
                ZodiacSign.CAPRICORN -> R.color.capricorn
                else -> R.color.invalid
            }
            val circleBg = zodiacTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))
            dateHistoryTextView.text = dateFormatter.format(Date(item.dateHistory))
            dateTextView.text = "Date: ${item.date} - Month: ${item.month}"
            dataTextView.text = root.context.getString(R.string.results_x,
                zodiacResults.zodiacSign.toString())
            deleteButton.setOnClickListener{
                deleteData(item.id, view.context)
            }
        }

        fun deleteData(id: Long, context: Context){
            val dbZodiac = ZodiacDb.getInstance(context)
            val dao = dbZodiac.dao
            MaterialAlertDialogBuilder(context)
                .setMessage(R.string.confirm_delete_history)
                .setPositiveButton(context.getString(R.string.delete)) { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO){
                            dao.clearDataZodiac(id)
                        }
                    }
                }
                .setNegativeButton(context.getString(R.string.cancel)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }
}
