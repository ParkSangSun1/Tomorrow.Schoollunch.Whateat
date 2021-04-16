package com.what.tomorrow_school_lunch.UI.main.fragments

import android.database.DatabaseUtils
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.main.adapters.AddSchoolRecyclerViewAdapter
import com.what.tomorrow_school_lunch.databinding.FragmentTodayPhraseBinding


class TodayPhraseFragment : Fragment() {

    lateinit var binding : FragmentTodayPhraseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_today_phrase, container, false)


        return binding.root
//        return inflater.inflate(R.layout.fragment_today_phrase, container, false)
    }


}