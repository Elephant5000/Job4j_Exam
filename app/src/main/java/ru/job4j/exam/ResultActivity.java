package ru.job4j.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private final QuestionStore store = QuestionStore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        LinearLayout linearLayoutVertical = findViewById(R.id.linearLayoutVertical);

        for (int index = 0; index < store.size(); index ++) {
            View view = getLayoutInflater().inflate(R.layout.result_question_item, null);

            TextView questionN = (TextView) view.findViewById(R.id.questionTV);
            TextView answerN = view.findViewById(R.id.answerTV);
            TextView userAnswerN = view.findViewById(R.id.userAnswerTV);

            questionN.setText(store.get(index).getText());
            answerN.setText(String.valueOf(store.get(index).getAnswer()));
            userAnswerN.setText(String.valueOf(store.get(index).getUserAnswer()));

            linearLayoutVertical.addView(view);
        }

        View view = getLayoutInflater().inflate(R.layout.back_button, null);
        Button button = view.findViewById(R.id.back);
        linearLayoutVertical.addView(view);
        button.setOnClickListener(this::backBtn);
    }

    private void backBtn(View view) {
        onBackPressed();
    }
}
