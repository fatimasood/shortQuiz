package com.example.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QueAnsClass extends AppCompatActivity {

    String[] questions,answers;
    TextView txtq,txta;
    Button bt1,bt2,bt3;
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que_ans_class);
        getSupportActionBar().hide();

        txtq=(TextView)findViewById(R.id.textView6);
        txta=(TextView)findViewById(R.id.textView7);
        bt1=(Button)findViewById(R.id.button1);
        bt2=(Button)findViewById(R.id.button2);
        bt3=(Button)findViewById(R.id.button3);

        questions=getResources().getStringArray(R.array.questionS);
        answers=getResources().getStringArray(R.array.answerS);
        index=0;
        txtq.setText(questions[index]);
        txta.setText("Press A for Answer");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                txtq.setText(questions[index]);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txta.setText(answers[index]);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txta.setText("Press A for Answer");
                index++;
                txtq.setText(questions[index]);
            }
        });
    }
}