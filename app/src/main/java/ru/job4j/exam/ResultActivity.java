package ru.job4j.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private final QuestionStore store = QuestionStore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        for (int index = 0; index < store.size(); index ++) {
            TextView question = findViewById(getResources().getIdentifier("questionTV" + String.valueOf(index), "id", this.getPackageName()));
            TextView answer = findViewById(getResources().getIdentifier("answerTV" + String.valueOf(index), "id", this.getPackageName()));
            TextView userAnswer = findViewById(getResources().getIdentifier("userAnswerTV" + String.valueOf(index), "id", this.getPackageName()));
            question.setText(store.get(index).getText());
            answer.setText(String.valueOf(store.get(index).getAnswer()));
            userAnswer.setText(String.valueOf(store.get(index).getUserAnswer()));
        }
        Button back = findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
    }

    private void backBtn(View view) {
        onBackPressed();
    }
}
