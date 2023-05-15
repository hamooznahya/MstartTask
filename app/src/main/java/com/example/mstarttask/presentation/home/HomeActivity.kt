package com.example.mstarttask.presentation.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import com.example.mstarttask.R
import com.example.mstarttask.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }



}