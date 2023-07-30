package com.shersar.mybankingapp.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.shersar.mybankingapp.R
import com.shersar.mybankingapp.databinding.FragmentHomeScreenBinding

class HomeScreen : Fragment(R.layout.fragment_home_screen) {
    val binding by viewBinding(FragmentHomeScreenBinding::class.java)

}