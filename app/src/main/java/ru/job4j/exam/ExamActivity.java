package ru.job4j.exam;

/**
 * Курс Job4j
 * @autor Устюжанин Павел
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ExamActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static int scrRotationCount = 0;
    private int position = 0;
    private final List<Question> questions = Arrays.asList(
            new Question(
                    1, "1. How many primitive variables does Java have?",
                    Arrays.asList(
                            new Option(1, "1.1"), new Option(2, "1.2"),
                            new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "2. What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "3. What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        scrRotationCount = (savedInstanceState == null) ? 0: savedInstanceState.getInt("screenRotation") + 1;
        position = (savedInstanceState == null) ? 0: savedInstanceState.getInt("position");
        Log.d(TAG, "OnCreate" + "       Rotation count - " + scrRotationCount);
        this.fillForm();
        Button next = findViewById(R.id.next);
        final RadioGroup variants = findViewById(R.id.variants);
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (variants.getCheckedRadioButtonId() != -1) {
                            saveAnswer();
                            showAnswer();
                            position++;
                            fillForm();
                        }
                    }
                }
        );
        final Button previous = findViewById(R.id.previous);
        previous.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (variants.getCheckedRadioButtonId() != -1) {
                        saveAnswer();
                        position--;
                        fillForm();
                    }
                }
            }
        );
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
        for (Question question: questions) {
            outState.putInt("question" + question.getId(), question.getUserAnswer());
            Log.d(TAG, "onSaveInstanceState" + "question" + question.getId() + "  " + question.getUserAnswer());
        }
        Log.d(TAG, "onSaveInstanceState" + "    Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (Question question: questions) {
            question.setUserAnswer(savedInstanceState.getInt("question" + question.getId()));
            Log.d(TAG, "onRestoreInstanceState" + "question" + question.getId() + "  " + savedInstanceState.getInt("question" + question.getId()));
        }
        Log.d(TAG, "onRestoreInstanceState" + "    Rotation count - " + scrRotationCount);
    }


    private void fillForm() {
        findViewById(R.id.previous).setEnabled(position != 0);
        findViewById(R.id.next).setEnabled(position != questions.size() - 1);
        final TextView text = findViewById(R.id.question);
        Question question = this.questions.get(this.position);
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
        Question question = this.questions.get(this.position);
        Toast.makeText(
                this, "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void saveAnswer() {
        Question question = this.questions.get(this.position);
        RadioGroup variants = findViewById(R.id.variants);
        question.setUserAnswer(variants.getCheckedRadioButtonId());
    }

}