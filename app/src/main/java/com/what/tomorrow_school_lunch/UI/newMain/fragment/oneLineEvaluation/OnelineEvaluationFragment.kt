package com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.main.adapters.AddSchoolRecyclerViewAdapter
import com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation.adapter.CustomPagerAdapter
import com.what.tomorrow_school_lunch.databinding.FragmentOnelineEvaluationBinding
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle


class OnelineEvaluationFragment : Fragment() {

    private var vpAdapter: FragmentStatePagerAdapter? = null

    lateinit var binding: FragmentOnelineEvaluationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_oneline_evaluation,
            container,
            false
        )


        vpAdapter = CustomPagerAdapter(childFragmentManager)

        binding.viewpager.adapter = vpAdapter
        binding.indicator.setViewPager(binding.viewpager)
//        binding.indicatorView.apply {
//            setSliderColor(0, 0)
//            setSliderWidth(resources.getDimension(R.dimen.dp_10))
//            setSliderHeight(resources.getDimension(R.dimen.dp_5))
//            setSlideMode(IndicatorSlideMode.WORM)
//            setIndicatorStyle(IndicatorStyle.CIRCLE)
//            setupWithViewPager(binding.bannerView)
//        }
//


//        binding.addSchoolRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
//        binding.addSchoolRecyclerView.adapter = AddSchoolRecyclerViewAdapter()

        return binding.root
    }

}