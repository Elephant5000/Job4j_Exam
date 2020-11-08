package ru.job4j.exam.resultactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import ru.job4j.exam.ExamsActivity;
import ru.job4j.exam.QuestionStore;
import ru.job4j.exam.R;
import ru.job4j.exam.examactivity.ExamActivity;

public class ResultFragment extends Fragment {

    private final QuestionStore store = QuestionStore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewResultFragment = inflater.inflate(R.layout.result_activity, container, false);

        LinearLayout linearLayoutVertical = viewResultFragment.findViewById(R.id.linearLayoutVertical);

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

        View viewBack = getLayoutInflater().inflate(R.layout.back_button, null);
        Button backBtn = viewBack.findViewById(R.id.back);
        linearLayoutVertical.addView(viewBack);
        backBtn.setOnClickListener(this::backBtn);

        View viewExamsList = getLayoutInflater().inflate(R.layout.exams_list_button, null);
        Button examsListBtn = viewExamsList.findViewById(R.id.examsList);
        linearLayoutVertical.addView(viewExamsList);
        examsListBtn.setOnClickListener(this::examsListBtn);

        return viewResultFragment;
    }

    private void backBtn(View view) {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }

    private void examsListBtn(View view) {
        Intent intent = new Intent(getActivity(), ExamsActivity.class);
        intent.putExtra(ExamActivity.HINT_FOR, store.getPosition());
        startActivity(intent);
    }
}
