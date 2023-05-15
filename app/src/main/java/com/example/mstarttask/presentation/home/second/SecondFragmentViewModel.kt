package com.example.mstarttask.presentation.home.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.domain.repository.DateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondFragmentViewModel
@Inject constructor(private val repository: DateRepository) : ViewModel() {

    private val _result = MutableSharedFlow<List<DateModel>>()
    val result = _result.asSharedFlow()

    fun getItems() {
        viewModelScope.launch {
            repository.getAllEvent().collectLatest {
                _result.emit(it)
            }
        }
    }

    fun updateItems(date: DateModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateEvent(date)
        }
    }

    fun onDeleteClicked(date: DateModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEvent(date)
        }
    }

    fun onDeleteSelected(date: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteById(date)
        }
    }
}