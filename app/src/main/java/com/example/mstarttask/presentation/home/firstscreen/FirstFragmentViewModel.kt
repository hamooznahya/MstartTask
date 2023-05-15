package com.example.mstarttask.presentation.home.firstscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mstarttask.data.dto.DateResponse
import com.example.mstarttask.domain.model.DateModel
import com.example.mstarttask.domain.repository.DateRepository
import com.example.mstarttask.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstFragmentViewModel
@Inject constructor(
    private val dateRepositories: DateRepository
) : ViewModel() {

    private val _result = MutableSharedFlow<ResponseState<DateResponse>>()
    val result = _result.asSharedFlow()

    private val _events = MutableSharedFlow<Events>()
    val events = _events.asSharedFlow()


    private fun sendEvent(event: Events) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }



        fun saveItem(
        date: DateModel,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dateRepositories.saveEvent(date)
        }
    }

    fun convertDate(date: String) {
        viewModelScope.launch {
            dateRepositories.convertDate(date).collectLatest {
                _result.emit(it)
            }
        }
    }
    fun showDialogEvent() {
        sendEvent(Events.ShowDialogEvent)
    }
    fun pickDateEvent() {
        sendEvent(Events.PickDateEvent)
    }

        sealed class Events {
            object ShowDialogEvent : Events()
            object PickDateEvent : Events()

        }

}
