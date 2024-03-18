package edu.oregonstate.cs492.githubsearchwithsettings.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ExerciseSearchResults(
    @Json(name = "name") val name: String,
    @Json(name = "equipment") val equipment: String,
    @Json(name = "instructions") val instructions: String
) : Serializable
