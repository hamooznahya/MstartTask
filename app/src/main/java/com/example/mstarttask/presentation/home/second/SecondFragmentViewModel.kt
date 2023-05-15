package com.example.mstarttask.presentation.home.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.domain.repository.DateRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SecondFragmentViewModel
@Inject constructor(private val repository: DateRepositories)
    : ViewModel() {

    private val _result = MutableSharedFlow<List<DateModel>>()
    val result = _result.asSharedFlow()

        fun getItems(){
            viewModelScope.launch {
                repository.getAllEvent().collectLatest {
                    _result.emit(it)
                }
            }
        }
        fun updateItems(date: DateModel) {
            viewModelScope.launch {
                repository.updateEvent(date)
            }
        }
    fun onDeleteClicked(date: DateModel) {
        repository.deleteEvent(date)
    }
    fun onDeleteSelected(date: List<Int>) {
        repository.deleteById(date)
    }
}