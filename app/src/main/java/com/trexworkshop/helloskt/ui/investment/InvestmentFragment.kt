package com.trexworkshop.helloskt.ui.investment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.trexworkshop.helloskt.R
import com.trexworkshop.helloskt.databinding.FragmentGalleryBinding
import com.trexworkshop.helloskt.databinding.FragmentInvestmentBinding
import java.util.*
import java.util.Calendar.*

class InvestmentFragment : Fragment() {
    private var _binding: FragmentInvestmentBinding? = null
    private val binding get() = _binding!!
    val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInvestmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDOB.setOnClickListener{
            val dateDialogFragment = DateDialogFragment{year,month,day -> onDateSelected(year,month,day)
            }
            dateDialogFragment.show(parentFragmentManager,"DatePicker")
        }
        binding.buttonCalculate.setOnClickListener{

        }
        binding.buttonReset.setOnClickListener{
            binding.buttonDOB.text = "Date of birth"
            binding.textViewAge.text = ""
            binding.textViewAmountInvestment.text = ""
        }

    }

    private fun onDateSelected(year: Int,month: Int,day: Int){
        binding.buttonDOB.text = String.format("%02d/%02d/%4d", day, month+1, year)
        //set year, month, day to be module level variable
        with(calendar){
            set(YEAR, year)
            set(MONTH, month)
            set(DAY_OF_MONTH, day)
        }
        val age = dayBetween(calendar, getInstance()).div(365)
        binding.textViewAge.text = age.toString()
    }

    fun dayBetween(startDate: Calendar, endDate: Calendar): Long{
        val date = startDate.clone() as Calendar
        var daysBetween: Long = 0
        while (date.before(endDate)) {
            date.add(DAY_OF_MONTH, 1)
            daysBetween++
        }
        return daysBetween
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class DateDialogFragment(val dateSetListener:(year: Int, month: Int, day: Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener{
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(YEAR)
            val month = c.get(MONTH)
            val day = c.get(DAY_OF_MONTH)

            return DatePickerDialog(requireContext(),this,year,month,day)
        }

        override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
            dateSetListener(year, month, day)
        }
    }
}