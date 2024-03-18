package edu.oregonstate.cs492.githubsearchwithsettings.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.oregonstate.cs492.githubsearchwithsettings.R
import edu.oregonstate.cs492.githubsearchwithsettings.data.ExerciseSearchResults

class ExerciseListAdapter(
    private val onExerciseClick: (ExerciseSearchResults) -> Unit
)
    : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>() {
    private var exerciseList = listOf<ExerciseSearchResults>()

    fun updateExerciseList(newExerciseList: List<ExerciseSearchResults>?) {
        notifyItemRangeRemoved(0, exerciseList.size)
        exerciseList = newExerciseList ?: listOf()
        notifyItemRangeInserted(0, exerciseList.size)
    }

    override fun getItemCount() = exerciseList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercise_list_item, parent, false)
        return ExerciseViewHolder(itemView, onExerciseClick)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    class ExerciseViewHolder(
        itemView: View,
        onClick: (ExerciseSearchResults) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val nameTV: TextView = itemView.findViewById(R.id.tv_name)
        private var currentExercise: ExerciseSearchResults? = null

        init {
            itemView.setOnClickListener {
                currentExercise?.let(onClick)
            }
        }


        fun bind(exercise: ExerciseSearchResults) {
            currentExercise = exercise
            nameTV.text = exercise.name
        }
    }
}