package com.what.tomorrow_school_lunch.UI.tutorial

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.what.tomorrow_school_lunch.R

private const val ARG_BACKGROUND_COLOR = "param1"
private const val ARG_RESOURCE = "param2"
private const val ARG_TITLE = "param3"

class TutorialFragment : Fragment() {
    private var param1: Int? = null
    private var param2: Int? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_BACKGROUND_COLOR)
            param2 = it.getInt(ARG_RESOURCE)
            param3 = it.getString(ARG_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tutorial, container, false).apply {
            setBackgroundColor(param1 ?: Color.RED)

            findViewById<LottieAnimationView>(R.id.lottieAnimationView).setAnimation(
                param2 ?: R.raw.welcome
            )
            findViewById<LottieAnimationView>(R.id.lottieAnimationView).repeatCount =
                LottieDrawable.INFINITE
            findViewById<LottieAnimationView>(R.id.lottieAnimationView).repeatMode =
                LottieDrawable.REVERSE
            findViewById<LottieAnimationView>(R.id.lottieAnimationView).playAnimation()

            findViewById<TextView>(R.id.fragment_textview).text =
                param3 ?: "튜토리얼 시작"

            findViewById<TextView>(R.id.fragment_textview_contents).text =
                param3 ?: "급뭐에 오신걸 환영합니다"
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: Int, param2: Int, param3: String) =
            TutorialFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_BACKGROUND_COLOR, param1)
                    putInt(ARG_RESOURCE, param2)
                    putString(ARG_TITLE, param3)
                }
            }
    }


}