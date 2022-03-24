package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class trueQuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiztrue.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public trueQuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " + trueQuizContract.QuestionsTable.TABLE_NAME + " ( " +
                trueQuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                trueQuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                trueQuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                trueQuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                trueQuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillquestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + trueQuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillquestionsTable() {
        question Q1 = new question(" Android is ", "an operating system", "a web browser", "web server", 1);
        addquestion(Q1);
        question Q2 = new question("For which of the following Android is mainly developed?", "laptops", "Mobile devices", "Servers", 2);
        addquestion(Q2);
        question Q3 = new question("Which of the following virtual machine is used by the Android operating system?", "JVM", "Simple virtual machine\n", "Dalvik virtual machine", 3);
        addquestion(Q3);
        question Q4 = new question("Android is based on which of the following language?", "java", "C++", "C", 1);
        addquestion(Q4);
        question Q5 = new question("On which of the following, developers can test the application, during developing the android applications?", "physical android phone", "All options", "third party emulator", 2);
        addquestion(Q5);
    }

    private void addquestion(question ques) {
        ContentValues con = new ContentValues();
        con.put(trueQuizContract.QuestionsTable.COLUMN_QUESTION, ques.getQues());
        con.put(trueQuizContract.QuestionsTable.COLUMN_OPTION1, ques.getOption1());
        con.put(trueQuizContract.QuestionsTable.COLUMN_OPTION2, ques.getOption2());
        con.put(trueQuizContract.QuestionsTable.COLUMN_ANSWER_NR, ques.getansNr());
        db.insert(trueQuizContract.QuestionsTable.TABLE_NAME, null, con);
    }

    @SuppressLint("Range")
    public List<question> getAllquestions() {
        List<question> QuestionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + trueQuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                question q = new question(" Android is an operating system", "true", "false", 1);
                q.setQues(c.getString(c.getColumnIndex(trueQuizContract.QuestionsTable.COLUMN_QUESTION)));
                q.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                q.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                q.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                QuestionList.add(q);
            } while (c.moveToNext());
        }

        c.close();
        return QuestionList;
    }
}
