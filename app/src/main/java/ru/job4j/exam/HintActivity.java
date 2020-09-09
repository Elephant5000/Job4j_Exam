package ru.job4j.exam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class HintActivity extends AppCompatActivity {
    private final Map<Integer, String> answer = new HashMap<Integer, String>();

    private final QuestionStore store = QuestionStore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.hint_activity);
        TextView hintTextView = findViewById(R.id.hint);
        TextView questionTextView = findViewById(R.id.question);
        int question = getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        hintTextView.setText(this.answer.get(question));
        questionTextView.setText(store.get(question).getText());
        Button back = findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
    }

    public HintActivity() {
        for (int index = 0; index < store.size(); index ++) {
            this.answer.put(index, "Answer -  " + store.get(index).getAnswer());
        }
    }

    private void backBtn(View view) {
        onBackPressed();
    }
}