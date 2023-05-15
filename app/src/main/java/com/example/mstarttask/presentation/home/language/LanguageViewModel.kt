package com.example.mstarttask.presentation.home.language

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mstarttask.domain.model.AppSettings
import com.example.mstarttask.domain.repository.CommonRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val repository: CommonRepository,

    ) : ViewModel() {

    private val _languageState = MutableLiveData<String>()
    val languageState: LiveData<String>
        get() = _languageState

    private val _changeLanguageState = MutableLiveData<AppSettings>()
    val changeLanguageState: LiveData<AppSettings>
        get() = _changeLanguageState


    fun changeLanguage(newLanguage: String) {
        viewModelScope.launch {
            repository.changeLanguage(newLanguage).collect{
                _changeLanguageState.value = it

            }

        }
    }

    fun getLanguage() {
        viewModelScope.launch {

            repository.getAppSettings().collect {
                _languageState.value = (it.language)
            }
        }
    }


}





