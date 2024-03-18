package edu.oregonstate.cs492.githubsearchwithsettings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.oregonstate.cs492.githubsearchwithsettings.data.ExerciseRepository
import edu.oregonstate.cs492.githubsearchwithsettings.data.ExerciseSearchResults
import edu.oregonstate.cs492.githubsearchwithsettings.data.ExerciseService
import edu.oregonstate.cs492.githubsearchwithsettings.util.LoadingStatus
import kotlinx.coroutines.launch

class ExerciseSearchViewModel : ViewModel() {
    private val repository = ExerciseRepository(ExerciseService.create())

    private val _searchResults = MutableLiveData<List<ExerciseSearchResults>?>(null)
    val searchResults: LiveData<List<ExerciseSearchResults>?> = _searchResults

    private val _loadingStatus = MutableLiveData<LoadingStatus>(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadSearchResults(name: String) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            val result = repository.loadExercisesSearch(name)
            _searchResults.value = result.getOrNull()
            _error.value = result.exceptionOrNull()?.message
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}