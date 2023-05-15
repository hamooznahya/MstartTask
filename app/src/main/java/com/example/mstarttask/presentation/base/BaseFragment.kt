package com.example.mstarttask.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mstarttask.R
import com.example.mstarttask.presentation.no_internet.NoInternetFragment
import com.example.mstarttask.utils.ResponseError
import com.example.mstarttask.utils.alert
import com.sellotion.driver.presentation.no_internet.RefreshCallBack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    companion object {
        const val INTERNET_TAG = "INTERNET_TAG"

    }

    fun handleError(it: ResponseError, callback: (() -> Unit)? = null) {
        when (it) {
            ResponseError.ERROR_SERVER_ERROR, ResponseError.ERROR_INTERNAL_ERROR -> {
                alert(
                    title = getString(R.string.error), message = it.errorMessage
                ) {
                    positiveButton(R.string.title_button_ok)
                }.show()
            }

            ResponseError.ERROR_NO_INTERNET_CONNECTION, ResponseError.ERROR_TIME_OUT -> {
                showNoInternetDialog {
                    callback?.invoke()
                }
            }
        }
    }

    private fun showNoInternetDialog(callback: () -> Unit) {
        val noInternetFragment = NoInternetFragment()
        noInternetFragment.refreshCallBack = object : RefreshCallBack {
            override fun onRefreshClicked() {
                callback.invoke()
            }

        }
        noInternetFragment.show(requireActivity().supportFragmentManager, INTERNET_TAG)
    }


}
