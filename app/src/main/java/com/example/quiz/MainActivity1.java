package com.example.quiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity1 extends AppCompatActivity {

    Button mbtn,tfbtn,rbtn,lbtn,qbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        getSupportActionBar().hide();

        mbtn=(Button) findViewById(R.id.Mcq);
        tfbtn=(Button) findViewById(R.id.truefalse);
        rbtn=(Button) findViewById(R.id.rate);
        lbtn=(Button) findViewById(R.id.learn);
        qbtn=(Button) findViewById(R.id.quesans);

        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this, MCQclass.class));
            }
        });

        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this, rateclass.class));
            }
        });

        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/"));
                startActivity(intent);
            }
        });

        tfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this, truefalseclass.class));
            }
        });

        qbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this, QueAnsClass.class));
            }
        });
    }
}