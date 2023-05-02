package com.example.codingnatorpoject

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.codingnatorpoject.databinding.FragmentStageOneChapterTenResultBinding

class StageOneChapterTenResultFragment : Fragment() {
    private var myAnswer: String? = null
    private var myquestion: String? = null
    private var myExample: String? = null
    private var order: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            myAnswer = it.getString("answer")
            myquestion = it.getString("question")
            myExample = it.getString("example")
            order = it.getInt("order")
        }
    }

    var binding: FragmentStageOneChapterTenResultBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStageOneChapterTenResultBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val color = Color.rgb(231, 137, 137)
        val bundle = Bundle()  //몇 챕터를 선택할지 이 번들에 넣어서 알려줍니다.

        if(myAnswer != null){  //answer만 넘어오면
            val str1 = "\"$myquestion\"은(는) "
            val str2 = "<$myAnswer>"
            val str3 = "입니다."
            val spannable = SpannableString("$str1$str2$str3")
            spannable.setSpan(ForegroundColorSpan(color), str1.length, str1.length + str2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding?.txtCorrectChapterTen?.text = "정답!"
            binding?.txtExplainChapterTen?.setText(spannable, TextView.BufferType.SPANNABLE)
            binding?.btnNextChapterTen?.setOnClickListener {
                order = order!! + 1  //즉, 여기서 order를 하나씩 올려준다.
                bundle.putInt("order", order!!)
                if(order == 2){  //챕터10의 ox가 두 문제 뿐이니, 이렇게 만들어줬습니다.
                    findNavController().navigate(R.id.action_stageOneChapterTenResultFragment_to_stageOneChapterTenQuizOXFragment, bundle)
                }
                else if (order == 11){  //챕터10이 다끝났다면
                    findNavController().navigate(R.id.action_stageOneChapterTenResultFragment_to_lastResultFragment)
                }
                else{
                    findNavController().navigate(R.id.action_stageOneChapterTenResultFragment_to_stageOneChapterTenQuizFourFragment, bundle)
                }
            }
        }

        if(myExample != null){  //즉, example이 같이 넘어오면 실행합니다.
            val str1 = "<$myExample> : 오답입니다.\n\n\"$myquestion\"은(는) "
            val str2 = "<$myAnswer>"
            val str3 = "입니다."
            val spannable = SpannableString("$str1$str2$str3")
            spannable.setSpan(ForegroundColorSpan(color), str1.length, str1.length + str2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding?.txtCorrectChapterTen?.setBackgroundColor(Color.rgb(231, 137,137))  //이렇게 rgb를 이용해 background의 색을 바꿉니다
            binding?.txtCorrectChapterTen?.text = "오답!"
            binding?.txtExplainChapterTen?.setText(spannable, TextView.BufferType.SPANNABLE)
            binding?.btnNextChapterTen?.setOnClickListener {
                order = order!! + 1  //즉, 여기서 order를 하나씩 올려준다.
                bundle.putInt("order", order!!)
                if(order == 2){  //챕터10의 ox가 두 문제 뿐이니, 이렇게 만들어줬습니다.
                    findNavController().navigate(R.id.action_stageOneChapterTenResultFragment_to_stageOneChapterTenQuizOXFragment, bundle)
                }
                else if (order == 11){  //챕터10이 다 끝났다면...
                    findNavController().navigate(R.id.action_stageOneChapterTenResultFragment_to_lastResultFragment)
                }
                else{
                    findNavController().navigate(R.id.action_stageOneChapterTenResultFragment_to_stageOneChapterTenQuizFourFragment, bundle)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}