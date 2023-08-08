package com.devdroid.quizzed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.devdroid.quizzed.databinding.ActivityQuizBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private lateinit var binding:ActivityQuizBinding
    private lateinit var list:ArrayList<QuestionModel>
    private var backPressedTime=0L
    private var count:Int=0
    private var score=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
            list= ArrayList<QuestionModel>()
        Firebase.firestore.collection("quiz")
            .get().addOnSuccessListener {
                doct->
                list.clear()
                for (i in doct.documents){

                    var questionModel=i.toObject(QuestionModel::class.java)
                    list.add(questionModel!!)
                }
                binding.question.setText(list.get(count).question)
                binding.option1.setText(list.get(count).option1)
                binding.option2.setText(list.get(count).option2)
                binding.option3.setText(list.get(count).option3)
                binding.option4.setText(list.get(count).option4)

            }
      //  list.add(QuestionModel("Who is the PM of India","Modi","Rahul Gandhi","Kejriwal","Siddaramaih","Modi"))
      //  list.add(QuestionModel("Who is the PM of India","Pratibha","Rajani","Kejriwal","Siddaramaih","Modi"))


        binding.option1.setOnClickListener{
            nextData(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener{
            nextData(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener{
            nextData(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener{
            nextData(binding.option4.text.toString())
        }
    }
    private fun nextData(i:String){
        if(count<=list.size) {
            if (list.get(count).ans.equals(i)) {
                score++
            }
        }
        count++
        if(count>=list.size){
            val intent = Intent(this,scoreActivity::class.java)
            intent.putExtra("Score",score)
            startActivity(intent)
            finish()
        }
        else {
            binding.question.setText(list.get(count).question)
            binding.option1.setText(list.get(count).option1)
            binding.option2.setText(list.get(count).option2)
            binding.option3.setText(list.get(count).option3)
            binding.option4.setText(list.get(count).option4)
        }
    }

    override fun onBackPressed() {

        if(backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed()
        }
        else{
            Toast.makeText(applicationContext,"Press back again to exit the app",Toast.LENGTH_SHORT).show()
        }
    }
}