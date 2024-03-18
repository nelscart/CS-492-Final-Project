package edu.oregonstate.cs492.githubsearchwithsettings.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository(
    private val service: ExerciseService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadExercisesSearch(name: String): Result<List<ExerciseSearchResults>> =
        withContext(ioDispatcher) {
            try {
                val response = service.searchExercises(name)
                if (response.isSuccessful) {
                    Result.success(response.body() ?: listOf())
                } else {
                    Result.failure(Exception(response.errorBody()?.string()))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}