package com.example.mstarttask.presentation.home.second

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mstarttask.R
import com.example.mstarttask.databinding.FragmentSecondScreenBinding
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.presentation.home.second.adapter.DateAdapter
import com.example.mstarttask.utils.observeInLifecycle
import com.example.mstarttask.utils.viewBinding
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second_screen), AdapterClick {

    private val binding by viewBinding { FragmentSecondScreenBinding.bind(requireView()) }
    private val viewModel by viewModels<SecondFragmentViewModel>()
    private lateinit var dateAdapter: DateAdapter
    private val dateList = mutableListOf<DateModel>()
    private var listPositionEvent = mutableListOf<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setMenuVisibility(false)
        setListener()
        setupViews()
        observeValue()
    }

    private fun setListener() {
        binding.addNewDateButton.setOnClickListener {
            findNavController().navigate(R.id.firstFragment)
        }
    }

    private fun setupViews() {
        dateAdapter = DateAdapter(dateList, this)
        binding.dateRecyclerView.adapter = dateAdapter
    }

    private fun observeValue() {
        viewModel.result.onEach {
            dateList.clear()
            dateList.addAll(it)
            dateAdapter.notifyDataSetChanged()
        }.observeInLifecycle(this)
    }

    override fun onDeleteClicked(dateModel: DateModel) {
        viewModel.onDeleteClicked(dateModel)
    }

    override fun onClickEdit(data: DateModel) {
        showDialogEvent(data)
    }

    override fun setIdEventSelected(id: Int) {

        if (listPositionEvent.contains(id)) {
            listPositionEvent.remove(id)
        } else {
            listPositionEvent.add(id)
        }

        setMenuVisibility(listPositionEvent.isNotEmpty());
    }


    private fun showDialogEvent(
        data: DateModel
    ) {
        val builder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .create()
        val view = layoutInflater.inflate(R.layout.edit_event_alert_dialog, null)

        val etEventName = view.findViewById<EditText>(R.id.etEventName)
        val etEventDescription = view.findViewById<EditText>(R.id.etEventDescription)
        val etHijriDate = view.findViewById<EditText>(R.id.etHijriDate)
        val etGregorianDate = view.findViewById<EditText>(R.id.etGregorianDate)
        val etServerTime = view.findViewById<EditText>(R.id.etServerTime)
        val btnSave = view.findViewById<MaterialButton>(R.id.btnSave)

        etEventName.setText(data.eventName)
        etEventDescription.setText(data.eventDescription)
        etHijriDate.setText(data.HijriDate)
        etGregorianDate.setText(data.GregorianDate)
        etServerTime.setText(data.serverDatetime)

        etHijriDate.isEnabled = false;
        etGregorianDate.isEnabled = false;
        etServerTime.isEnabled = false;



        btnSave.setOnClickListener {
            val date = DateModel(
                id = data.id,
                HijriDate = etHijriDate.text.toString(),
                GregorianDate = etGregorianDate.text.toString(),
                eventName = etEventName.text.toString(),
                eventDescription = etEventDescription.text.toString(),
                serverDatetime = etServerTime.text.toString()
            )
            viewModel.updateItems(
                date
            )
            builder.dismiss()

        }
        builder.setView(view)
        builder.setCanceledOnTouchOutside(true)
        builder.show()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater: MenuInflater = activity?.menuInflater!!
        inflater.inflate(R.menu.menu, menu)
        val myMenuItem = menu.findItem(R.id.action_delete)
        myMenuItem.setOnMenuItemClickListener {
            viewModel.onDeleteSelected(listPositionEvent)
            true
        }
    }

}