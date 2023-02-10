package com.example.myquizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.myquizapp.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    data class Question(
        val text: String, val answers: List<String>
    )

    private val question: MutableList<Question> = mutableListOf(
        Question(
            text = "Who was the first President of India ?",
            answers = listOf(  "Dr. Rajendra Prasad", "Abdul Kalam", "Lal Bahadur Shastri", "Zakir Hussain")
        ),
        Question(
            text = "What is android jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries")
        ),
        Question(
            text = "Who was the first Indian woman in Space ?",
            answers = listOf("Kalpana Chawla", "Sunita Williams", "Koneru Humpy", "None of the above")
        ),
        Question(
            text = "what is capital of India?",
            answers = listOf( "New Delhi","Uttar Pradesh", "Madhya Pradesh", "Kanpur")
        ),
        Question(
            text = "Who wrote the Indian National Anthem",
            answers = listOf(  "Rabindranath Tagore","Bakim Chandra Chatterji", "Swami Vivekanand","None of the above",)
        ),
        )


    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private var totalQuestion = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentQuizBinding>(
            inflater, R.layout.fragment_quiz, container, false
        )

        binding.questions = this
        randomizeQuestion()

        binding.nextBtn.setOnClickListener {
//            val checkId = binding.radioGroup.checkedRadioButtonId
//            if (checkId != 1) {
//                var answerIndex = 0
//
//                when (checkId) {
//                    R.id.answer_2nd -> answerIndex = 1
//                    R.id.answer_3rd -> answerIndex = 2
//                    R.id.answer_4th -> answerIndex = 3
//                }
//                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++

                    if (questionIndex < totalQuestion) {
                        currentQuestion = question[questionIndex]
                        setQuestion()
                        binding.invalidateAll()

                    } else {
                        it.findNavController().navigate(R.id.action_quizFragment_to_winFragment)
                    }

//                } else {
//                    it.findNavController().navigate(R.id.action_quizFragment_to_quizOverFragment)
//                }
//
//            }
        }

        return binding.root
    }

    private fun randomizeQuestion() {
        question.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = question[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
    }

    }

