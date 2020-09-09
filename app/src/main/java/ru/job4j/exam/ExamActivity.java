package ru.job4j.exam;

/**
 * Курс Job4j
 * @autor Устюжанин Павел
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int scrRotationCount = 0;
    private int position = 0;
    private final QuestionStore store = QuestionStore.getInstance();
    public static final String HINT_FOR = "hint_for";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        scrRotationCount = (savedInstanceState == null) ? 0: savedInstanceState.getInt("screenRotation") + 1;
        position = (savedInstanceState == null) ? 0: savedInstanceState.getInt("position");
        Log.d(TAG, "OnCreate" + "       Rotation count - " + scrRotationCount);
        this.fillForm();
        final Button next = findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        final Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(this::prevBtn);
        final Button help = findViewById(R.id.help);
        help.setOnClickListener(this::helpBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart" + "        Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume" + "       Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause" + "        Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop" + "         Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "OnDestroy" + "      Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("screenRotation", scrRotationCount);
        outState.putInt("position", position);
        Log.d(TAG, "onSaveInstanceState" + "    Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState" + "    Rotation count - " + scrRotationCount);
    }

    private void nextBtn(View view) {
        final RadioGroup variants = findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId() != -1) {
            saveAnswer();
            if (position < store.size() - 1) {
                showAnswer();
                position++;
                fillForm();
            } else {
                Intent intent = new Intent(ExamActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        }
    }

    private void prevBtn(View view) {
        final RadioGroup variants = findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId() != -1) {
            saveAnswer();
            position--;
            fillForm();
        }
    }

    private void helpBtn(View view) {
        Intent intent = new Intent(ExamActivity.this, HintActivity.class);
        intent.putExtra(HINT_FOR, position);
        startActivity(intent);
    }

    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        final TextView text = findViewById(R.id.question);
        Question question = this.store.get(this.position);
        text.setText(question.getText());
        RadioGroup variants = findViewById(R.id.variants);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
        variants.check(question.getUserAnswer());
    }

    private void showAnswer() {
        RadioGroup variants = findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.store.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void saveAnswer() {
        Question question = this.store.get(this.position);
        RadioGroup variants = findViewById(R.id.variants);
        question.setUserAnswer(variants.getCheckedRadioButtonId());
    }

}