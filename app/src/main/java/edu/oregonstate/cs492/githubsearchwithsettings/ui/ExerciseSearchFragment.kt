package edu.oregonstate.cs492.githubsearchwithsettings.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import edu.oregonstate.cs492.githubsearchwithsettings.R
import edu.oregonstate.cs492.githubsearchwithsettings.data.ExerciseSearchResults
import edu.oregonstate.cs492.githubsearchwithsettings.util.LoadingStatus
import edu.oregonstate.cs492.githubsearchwithsettings.util.buildGitHubQuery

class ExerciseSearchFragment : Fragment(R.layout.fragment_exercise_search) {
    private val viewModel: ExerciseSearchViewModel by viewModels()
    private val adapter = ExerciseListAdapter(::onExerciseClicked)

    private lateinit var searchResultsListRV: RecyclerView
    private lateinit var searchErrorTV: TextView
    private lateinit var loadingIndicator: CircularProgressIndicator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBoxET: EditText = view.findViewById(R.id.et_search_box)
        val searchBtn: Button = view.findViewById(R.id.btn_search)

        searchErrorTV = view.findViewById(R.id.tv_search_error)
        loadingIndicator = view.findViewById(R.id.loading_indicator)

        searchResultsListRV = view.findViewById(R.id.rv_search_results)
        searchResultsListRV.layoutManager = LinearLayoutManager(requireContext())
        searchResultsListRV.setHasFixedSize(true)

        searchResultsListRV.adapter = adapter

        viewModel.searchResults.observe(viewLifecycleOwner) {
                searchResults -> adapter.updateExerciseList(searchResults)
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) {
                loadingStatus ->
            when (loadingStatus) {
                LoadingStatus.LOADING -> {
                    searchResultsListRV.visibility = View.INVISIBLE
                    loadingIndicator.visibility = View.VISIBLE
                    searchErrorTV.visibility = View.INVISIBLE
                }

                LoadingStatus.ERROR -> {
                    searchResultsListRV.visibility = View.INVISIBLE
                    loadingIndicator.visibility = View.INVISIBLE
                    searchErrorTV.visibility = View.VISIBLE
                }

                else -> {
                    searchResultsListRV.visibility = View.VISIBLE
                    loadingIndicator.visibility = View.INVISIBLE
                    searchErrorTV.visibility = View.INVISIBLE
                }
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
                error -> searchErrorTV.text = getString(
            R.string.search_error,
            error
        )
        }

//        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        searchBtn.setOnClickListener {
            val query = searchBoxET.text.toString()
            if (!TextUtils.isEmpty(query)) {
                viewModel.loadSearchResults(query)
                searchResultsListRV.scrollToPosition(0)
            }
        }
    }

    private fun onExerciseClicked(exercise: ExerciseSearchResults) {
        val directions = ExerciseSearchFragmentDirections.navigateToExerciseDetails(exercise)
        findNavController().navigate(directions)
    }


}