package com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentStatePagerAdapter
import com.what.tomorrow_school_lunch.R
import com.what.tomorrow_school_lunch.UI.newMain.fragment.oneLineEvaluation.adapter.CustomPagerAdapter
import com.what.tomorrow_school_lunch.databinding.FragmentMealEvaluationBinding


class MealEvaluationFragment : Fragment() {
    private var image: Int? = null
    private var todayText : String? = null
    private var evaluationText: String? = null
    lateinit var binding: FragmentMealEvaluationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getInt("image", 0)
            evaluationText = it.getString("ev_text", "")
            todayText = it.getString("to_text","")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_meal_evaluation, container, false)


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.imageView.setImageResource(image!!)
        binding.imageView.setAnimation(image!!)
        binding.evTextView.text = evaluationText
        binding.todayTextView.text = todayText
    }

    companion object {
        fun newInstance(image: Int, to_text: String, ev_text:String) =
            MealEvaluationFragment().apply {
                arguments = Bundle().apply {
                    putInt("image", image)
                    putString("to_text",to_text)
                    putString("ev_text", ev_text)
                }
            }
    }
}