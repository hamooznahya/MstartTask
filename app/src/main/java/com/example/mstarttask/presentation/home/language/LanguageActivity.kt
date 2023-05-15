package com.example.mstarttask.presentation.home.language

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.mstarttask.R
import com.example.mstarttask.constants.Constants
import com.example.mstarttask.databinding.FragmentLanguageBinding
import com.example.mstarttask.presentation.base.BaseActivity
import com.example.mstarttask.presentation.home.HomeActivity
import com.example.mstarttask.utils.observeInLifecycle
import com.wx.wheelview.adapter.ArrayWheelAdapter
import com.wx.wheelview.widget.WheelView
import com.wx.wheelview.widget.WheelView.WheelViewStyle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LanguageActivity : BaseActivity() {

    private lateinit var binding: FragmentLanguageBinding
    private val viewModel by viewModels<LanguageViewModel>()
    private lateinit var arrayWheelAdapter: ArrayWheelAdapter

    //    override fun getTheme(): Int = R.style.DialogWhiteStatus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getLanguage()
        initListeners()
        observeValues()
        initWheelLanguage()
    }


    private fun initWheelLanguage() {

        val list = listOf(getString(R.string.english), getString(R.string.arabic))
        val style = WheelViewStyle()
        style.selectedTextSize = 20
        style.textSize = 16
        binding.wheelview.skin = WheelView.Skin.Holo // common皮肤
        style.backgroundColor = ContextCompat.getColor(this, R.color.blue)
        style.textColor = Color.parseColor("#EBEBEB")
        style.selectedTextColor = Color.WHITE
        style.holoBorderColor = Color.parseColor("#EBEBEB")
        binding.wheelview.style = style
        arrayWheelAdapter = ArrayWheelAdapter(this)
        binding.wheelview.setWheelAdapter(arrayWheelAdapter) // 文本数据源
        binding.wheelview.setWheelData(list)  // 数据集合
    }

    private fun initListeners() {
        binding.buttonSave.setOnClickListener {
            changeLanguage()

        }
    }

    private fun changeLanguage() {
        viewModel.changeLanguage(if (binding.wheelview.currentPosition == 0) "En" else "Ar")

    }

    private fun observeValues() {
        viewModel.changeLanguageState.observe(this) {
            intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewModel.languageState.observe(this) {
            if (it == Constants.ENGLISH)
                binding.wheelview.selection = 0
            else
                binding.wheelview.selection = 1
        }
    }

}