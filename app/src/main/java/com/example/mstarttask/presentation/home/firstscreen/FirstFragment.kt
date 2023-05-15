package com.example.mstarttask.presentation.home.firstscreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.example.mstarttask.R
import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.databinding.FragmentFirstScreenBinding
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.presentation.base.BaseFragment
import com.example.mstarttask.utils.ResponseState
import com.example.mstarttask.utils.hide
import com.example.mstarttask.utils.observeInLifecycle
import com.example.mstarttask.utils.show
import com.example.mstarttask.utils.viewBinding
import com.example.mstarttask.utils.visible
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.onEach
import java.util.Calendar
import java.util.Locale


class FirstFragment : BaseFragment(R.layout.fragment_first_screen) {


    private val binding by viewBinding { FragmentFirstScreenBinding.bind(requireView()) }
    private val viewModel by viewModels<FirstFragmentViewModel>()
    private lateinit var date: String
    private lateinit var dateResponse: DateResponse
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        observeValue()
    }

    private fun observeValue() {
        viewModel.result.onEach {
            when (it) {
                is ResponseState.Failure -> {
                    binding.loading.hide()
                    handleError(it.error)
                }

                ResponseState.Loading -> {
                    binding.loading.show()
                }

                is ResponseState.Success -> {
                    binding.loading.hide()
                    binding.tvSelectedDate.text = it.item.hijri.date
                    binding.btnAddToDataBase.visible()
                    dateResponse = it.item
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)



        viewModel.events.onEach {
            when (it) {
                is FirstFragmentViewModel.Events.ShowDialogEvent -> {
                    showDialogEvent()
                }

                is FirstFragmentViewModel.Events.PickDateEvent -> {
                    showDatePicker()
                }

            }
        }.observeInLifecycle(viewLifecycleOwner)

    }

    @SuppressLint("SetTextI18n")
    private fun setListener() {

        binding.btnAddToDataBase.setOnClickListener {
            viewModel.showDialogEvent()
        }
        binding.btnPickDate.setOnClickListener {
            viewModel.pickDateEvent()
        }
    }

    private fun showDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                date = "$dayOfMonth-${(monthOfYear + 1)}-$year"
                viewModel.convertDate(date)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showDialogEvent() {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.add_event_alert_dialog, null)
        val etEventName = view.findViewById<EditText>(R.id.etEventName)
        val etEventDescription = view.findViewById<EditText>(R.id.etEventDescription)
        val btnAddEvent = view.findViewById<MaterialButton>(R.id.btnAddEvent)

        btnAddEvent.setOnClickListener {
            if (etEventName.text?.isNotEmpty() == true && etEventName.text?.isNotEmpty() == true) {
                var date = DateModel(
                    HijriDate = dateResponse.hijri.date,
                    GregorianDate = dateResponse.gregorian.date,
                    eventName = etEventName.text.toString(),
                    eventDescription = etEventDescription.text.toString(),
                    serverDatetime = ""
                )
                viewModel.saveItem(date)
                builder.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please fill in the required fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()

    }
}