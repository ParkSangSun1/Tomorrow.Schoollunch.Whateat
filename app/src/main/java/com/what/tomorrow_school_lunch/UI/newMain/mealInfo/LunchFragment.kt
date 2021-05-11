package com.what.tomorrow_school_lunch.UI.newMain.mealInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.main.adapters.AddSchoolRecyclerViewAdapter
import com.what.tomorrow_school_lunch.databinding.FragmentLunchBinding


class LunchFragment : Fragment() {
  lateinit var binding :FragmentLunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lunch, container, false)

        binding.addSchoolRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.addSchoolRecyclerView.adapter = AddSchoolRecyclerViewAdapter()

        return inflater.inflate(R.layout.fragment_lunch, container, false)
    }


}