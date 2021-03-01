package com.example.cinemacollection.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.cinemacollection.viewmodel.AppState
import com.example.cinemacollection.model.Cinema
import com.example.cinemacollection.databinding.MainFragmentBinding
import com.example.cinemacollection.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderMyData(it) })
        viewModel.getCinemaData()
    }

    private fun renderMyData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val cinemaData = appState.dataCinema
                binding.loading.visibility = View.GONE
                setData(cinemaData)
            }
            is AppState.Loading -> {
                binding.loading.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loading.visibility = View.GONE
                Snackbar
                    .make(binding.main, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getCinemaData() }
                    .show()
            }
        }
    }

    private fun setData(cinemaData: Cinema) {
        Snackbar.make(binding.main, "Success", Snackbar.LENGTH_LONG).show()
    }
}