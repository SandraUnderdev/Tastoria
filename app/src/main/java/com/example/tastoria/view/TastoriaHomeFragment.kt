package com.example.tastoria.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tastoria.databinding.FragmentTastoriaHomeBinding


class TastoriaHomeFragment : Fragment() {

    private lateinit var binding: FragmentTastoriaHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTastoriaHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exploreButton.setOnClickListener {
            val action = TastoriaHomeFragmentDirections.actionNavHomeToRecipeListFragment()
            findNavController().navigate(action)
        }


    }

}