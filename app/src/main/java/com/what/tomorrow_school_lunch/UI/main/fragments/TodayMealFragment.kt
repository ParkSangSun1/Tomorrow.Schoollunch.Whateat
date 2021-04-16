package com.what.tomorrow_school_lunch.UI.main.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.main.adapters.AddSchoolRecyclerViewAdapter
import com.what.tomorrow_school_lunch.databinding.FragmentTodayMealBinding


class TodayMealFragment : Fragment() {

    lateinit var binding : FragmentTodayMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_meal, container, false)

        binding.addSchoolRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.addSchoolRecyclerView.adapter = AddSchoolRecyclerViewAdapter()

        return binding.root
    }

}