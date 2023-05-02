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
import com.example.codingnatorpoject.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private var myAnswer: String? = null
    private var myquestion: String? = null
    private var myExample: String? = null
    private var chapterNumber: Int? = null
    private var quizFourComplete: Int? = null   //4지선다 퀴즈의 두 번쨰 문제인지 확인해주는 변수(즉, ox퀴즈에서 막 넘어 왔으면 이 값은 null이다)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            myAnswer = it.getString("answer")
            myquestion = it.getString("question")
            myExample = it.getString("example")
            chapterNumber = it.getInt("chapterNumber")
            quizFourComplete = it.getInt("quizFourComplete")
        }
    }

    var binding: FragmentResultBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)
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
            binding?.correctTxt?.text = "정답!"
            binding?.explainTxt?.setText(spannable, TextView.BufferType.SPANNABLE)
            binding?.btnNext?.setOnClickListener {

                bundle.putInt("chapterNumber", chapterNumber!!)
                if(quizFourComplete == 200){
                    findNavController().navigate(R.id.action_resultFragment_to_lastResultFragment)
                }
                else if(quizFourComplete == 100){
                    bundle.putInt("quizFourComplete", 100)  //2번째 4지선다인지 확인용
                    findNavController().navigate(R.id.action_resultFragment_to_fourChooseQuizFragment, bundle)
                }
                else{
                    findNavController().navigate(R.id.action_resultFragment_to_fourChooseQuizFragment, bundle)
                }

                /*
                if(quizFourComplete == 200){  //해당 챕터가 다 끝났다는 신호를 받았다면,
                    findNavController().navigate(R.id.action_resultFragment_to_lastResultFragment)
                }
                 */
                /*  정상작동버젼
                bundle.putInt("chapterNumber", chapterNumber!!)  //현재 챕터를 알기위해 계속 chapterNumber를 넣어줍니다
                if(quizFourComplete == 100)
                    bundle.putInt("quizFourComplete", 100)  //2번째 4지선다인지 확인용
                findNavController().navigate(R.id.action_resultFragment_to_quizFourFragment, bundle)
                 */
            }
        }

        if(myExample != null){  //즉, example이 같이 넘어오면 실행합니다.
            val str1 = "<$myExample> : 오답입니다.\n\n\"$myquestion\"은(는) "
            val str2 = "<$myAnswer>"
            val str3 = "입니다."
            val spannable = SpannableString("$str1$str2$str3")
            spannable.setSpan(ForegroundColorSpan(color), str1.length, str1.length + str2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding?.correctTxt?.setBackgroundColor(Color.rgb(231, 137,137))  //이렇게 rgb를 이용해 background의 색을 바꿉니다
            binding?.correctTxt?.text = "오답!"
            binding?.explainTxt?.setText(spannable, TextView.BufferType.SPANNABLE)
            binding?.btnNext?.setOnClickListener {
                bundle.putInt("chapterNumber", chapterNumber!!)
                if(quizFourComplete == 200){
                    findNavController().navigate(R.id.action_resultFragment_to_lastResultFragment)
                }
                else if(quizFourComplete == 100){
                    bundle.putInt("quizFourComplete", 100)  //2번째 4지선다인지 확인용
                    findNavController().navigate(R.id.action_resultFragment_to_fourChooseQuizFragment, bundle)
                }
                else{
                    findNavController().navigate(R.id.action_resultFragment_to_fourChooseQuizFragment, bundle)
                }
                /* 정상작동버젼
                bundle.putInt("chapterNumber", chapterNumber!!)  //현재 챕터를 알기위해 계속 chapterNumber를 넣어줍니다
                if(quizFourComplete == 100)  //4지선다 문제가 다 끝났음을 100으로서 확인
                    bundle.putInt("quizFourComplete", 100)  //2번째 4지선다인지 확인용
                findNavController().navigate(R.id.action_resultFragment_to_quizFourFragment, bundle)
                 */
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}