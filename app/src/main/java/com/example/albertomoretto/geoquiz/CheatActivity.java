package com.example.albertomoretto.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE="com.example.albertomoretto.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_IS_SHOWN = "com.example.albertomoretto.geoquiz.answer_shown";
    private static final String KEY_IS_ANSWER_SHOW = "is Answer show";
    private static final String TAG = "CheatActivity";

    private boolean mAnswerIsTrue;
    private boolean mIsAnswerShow;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        if(savedInstanceState != null) {
            mIsAnswerShow = savedInstanceState.getBoolean(KEY_IS_ANSWER_SHOW,false);
            setAnswerShownResult(mIsAnswerShow);
        }

        if(mIsAnswerShow) {
            if(mAnswerIsTrue) {
                mAnswerTextView.setText(R.string.true_button);
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }
            mIsAnswerShow = true;
            setAnswerShownResult(mIsAnswerShow);
        }

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mIsAnswerShow = true;
                setAnswerShownResult(mIsAnswerShow);
            }
        });


    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN,isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause is called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState called");
        outState.putBoolean(KEY_IS_ANSWER_SHOW,mIsAnswerShow);
    }
}
