package edu.oregonstate.cs492.githubsearchwithsettings.ui

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.oregonstate.cs492.githubsearchwithsettings.R
import java.text.SimpleDateFormat
import java.util.*

class HomePageFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomePageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_page, container, false)

        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val dates = generateDates()
        adapter = HomePageAdapter(dates)
        recyclerView.adapter = adapter

        return rootView
    }

    private fun generateDates(): List<String> {
        val dates = mutableListOf<String>()
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(15) {
            dates.add(sdf.format(calendar.time))
            calendar.add(Calendar.DAY_OF_MONTH, -1) // Go back one day
        }

        return dates
    }
}
