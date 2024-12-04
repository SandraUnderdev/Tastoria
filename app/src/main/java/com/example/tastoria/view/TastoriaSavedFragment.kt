package com.example.tastoria.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tastoria.databinding.FragmentTastoriaSavedBinding



class TastoriaSavedFragment : Fragment() {
    private lateinit var binding: FragmentTastoriaSavedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTastoriaSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

}
