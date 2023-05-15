package com.example.mstarttask.presentation.home.second

import com.example.mstarttask.data.entity.DateEntity
import com.example.mstarttask.domain.model.DateModel

interface AdapterClick {
    fun onDeleteClicked(dateEntity: DateModel)

    fun onClickEdit(data: DateModel)

    fun setIdEventSelected(id : Int)
}