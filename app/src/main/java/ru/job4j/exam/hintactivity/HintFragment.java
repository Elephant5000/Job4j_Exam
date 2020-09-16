package ru.job4j.exam.hintactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import ru.job4j.exam.QuestionStore;
import ru.job4j.exam.R;
import ru.job4j.exam.examactivity.ExamActivity;

public class HintFragment extends Fragment {

    private View viewHintFragment = null;
    private final Map<Integer, String> answer = new HashMap<Integer, String>();

    private final QuestionStore store = QuestionStore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewHintFragment = inflater.inflate(R.layout.hint_activity, container, false);

        for (int index = 0; index < store.size(); index ++) {
            this.answer.put(index, "Answer -  " + store.get(index).getAnswer());
        }

        TextView hintTextView = viewHintFragment.findViewById(R.id.hint);
        TextView questionTextView = viewHintFragment.findViewById(R.id.question);
        int question = Objects.requireNonNull(getActivity()).getIntent().getIntExtra(ExamActivity.HINT_FOR, 0);
        hintTextView.setText(this.answer.get(question));
        questionTextView.setText(store.get(question).getText());
        Button back = viewHintFragment.findViewById(R.id.back);
        back.setOnClickListener(this::backBtn);
        return viewHintFragment;
    }

    private void backBtn(View view) {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }

}