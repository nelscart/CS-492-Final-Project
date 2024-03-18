package edu.oregonstate.cs492.githubsearchwithsettings.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import edu.oregonstate.cs492.githubsearchwithsettings.R

class ExerciseDetailFragment : Fragment(R.layout.fragment_exercise_detail) {
    private val args: ExerciseDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exerciseNameTV: TextView = view.findViewById(R.id.tv_exercise_name)
        val exerciseEquipmentTV: TextView = view.findViewById(R.id.tv_exercise_equipment)
        val exerciseInstructionsTV: TextView = view.findViewById(R.id.tv_exercise_instructions)

        exerciseNameTV.text = args.exercise.name
        exerciseEquipmentTV.text = "Equipment Needed: ${args.exercise.equipment}"
        exerciseInstructionsTV.text = "Exercise Intructions: ${args.exercise.instructions}"

    }
}