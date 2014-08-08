package com.jaredub.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jared on 8/8/2014.
 */
public class CheatActivity extends Activity{

    public static final String EXTRA_ANSWER_IS_TRUE = "com.jaredub.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.jaredub.android.geoquiz.answer_shown";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mshowAnswer;
    private static final String KEY_CHEATVALUE = "cheatvalue";
    private static final String TAG = "CheatActivity";
    private boolean mCheater;

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mCheater = true;
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mshowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mshowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(mCheater);

            }
        });

        if (savedInstanceState != null) {
            mCheater = savedInstanceState.getBoolean(KEY_CHEATVALUE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onSavedInstanceState");
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATVALUE, mCheater);
    }
}
