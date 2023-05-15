package com.sellotion.driver.presentation.no_internet

import java.io.Serializable

interface RefreshCallBack : Serializable {
    fun onRefreshClicked()
}