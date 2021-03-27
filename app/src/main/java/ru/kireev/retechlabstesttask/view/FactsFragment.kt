package ru.kireev.retechlabstesttask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kireev.retechlabstesttask.databinding.FragmentFactsBinding
import ru.kireev.retechlabstesttask.view.adapter.factadapter.FactListAdapter
import ru.kireev.retechlabstesttask.viewmodel.AppState
import ru.kireev.retechlabstesttask.viewmodel.FactViewModel
import ru.kireev.retechlabstesttask.viewmodel.entity.Fact

class FactsFragment(
    private val viewModel: FactViewModel
) : Fragment() {

    private var _binding: FragmentFactsBinding? = null
    private val binding get() = _binding!!

    private val listFacts = mutableListOf<Fact>()

    private val adapter = FactListAdapter()

    fun loadFact() {
        viewModel.loadFact()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.data.observe(viewLifecycleOwner, {
            handleState(it)
        })
    }

    private fun initViews() {
        binding.recyclerFacts.layoutManager = LinearLayoutManager(binding.recyclerFacts.context)
        binding.recyclerFacts.adapter = adapter
    }

    private fun handleState(state: AppState) {
        when (state) {
            is AppState.Success<*> -> refreshFacts(state.data as Fact)
            is AppState.Error -> showError(state.error.localizedMessage)
            is AppState.Loading -> showProgress(true)
        }
    }

    private fun refreshFacts(fact: Fact) {
        showProgress(false)
        listFacts.add(fact)
        val freshList = mutableListOf<Fact>()
        freshList.addAll(listFacts)
        adapter.submitList(freshList)
    }

    private fun showError(message: String?) {
        showProgress(false)
        Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(isLoading: Boolean) {
        val visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        binding.progressFacts.visibility = visibility
    }

}