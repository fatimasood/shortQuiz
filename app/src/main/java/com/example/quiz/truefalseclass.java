package com.example.quiz;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class truefalseclass extends AppCompatActivity {
    private TextView textQuestion;
    private TextView textScore;
    private TextView textQuestionCount;
    private TextView textCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private Button bConfirmNext;

    private ColorStateList textColorDefaultRb;

    private List<question> questionList;
    private int quesCounter;
    private int quesCountTotal;
    private question currentquestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truefalseclass);
        getSupportActionBar().hide();

        textQuestion = findViewById(R.id.text_view_question);
        textScore = findViewById(R.id.text_view_score);
        textQuestionCount = findViewById(R.id.text_view_question_count);
        textCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        bConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = rb1.getTextColors();

        trueQuizDbHelper dbHelper = new trueQuizDbHelper(this);
        questionList = dbHelper.getAllquestions();
        quesCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        bConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(truefalseclass.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if (quesCounter < quesCountTotal) {
            currentquestion = questionList.get(quesCounter);

            textQuestion.setText(currentquestion.getQues());
            rb1.setText(currentquestion.getOption1());
            rb2.setText(currentquestion.getOption2());

            quesCounter++;
            textQuestionCount.setText(quesCounter + "/" + quesCountTotal);
            answered = false;
            bConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentquestion.getansNr()) {
            score++;
            textScore.setText("Score: " + score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);

        switch (currentquestion.getansNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
        }

        if (quesCounter < quesCountTotal) {
            bConfirmNext.setText("Next");
        } else {
            bConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        finish();
    }
}