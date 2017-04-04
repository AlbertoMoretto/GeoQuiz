package com.example.albertomoretto.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_turkey,false),
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
    }

    private void checkAnswer(boolean userPressedTrue) {
        if(userPressedTrue == mQuestionBank[mCurrentIndex].isAnswerTrue()) {
            Toast.makeText(QuizActivity.this,R.string.correct_toast,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(QuizActivity.this,R.string.wrong_toast,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mPrevButton = (ImageButton) findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mCurrentIndex!= 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }
                else {
                    mCurrentIndex = mQuestionBank.length -1 ;
                }
                updateQuestion();
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }
}
