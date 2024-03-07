package edu.oregonstate.cs492.githubsearchwithsettings.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.oregonstate.cs492.githubsearchwithsettings.R
import androidx.navigation.fragment.findNavController
import androidx.navigation.NavController



class HomePageAdapter(private val dates: List<String>, private val navController: NavController) : RecyclerView.Adapter<HomePageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.log_workout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]
        holder.dateTextView.text = date
        holder.itemView.setOnClickListener {
            // Navigate to the workout detail fragment here
            val action = HomePageFragmentDirections.navigateToWorkoutDetail()
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }
}
