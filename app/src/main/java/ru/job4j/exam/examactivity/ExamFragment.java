package ru.job4j.exam.examactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import ru.job4j.exam.Option;
import ru.job4j.exam.Question;
import ru.job4j.exam.QuestionStore;
import ru.job4j.exam.R;
import ru.job4j.exam.hintactivity.HintActivity;
import ru.job4j.exam.resultactivity.ResultActivity;


public class ExamFragment extends Fragment {

    private int position = 0;
    private final QuestionStore store = QuestionStore.getInstance();
    private View viewExamFragment = null;


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewExamFragment = inflater.inflate(R.layout.exam_activity, container, false);
        position = (savedInstanceState == null) ? 0: savedInstanceState.getInt("position");
        this.fillForm();
        final Button next = viewExamFragment.findViewById(R.id.next);
        next.setOnClickListener(this::nextBtn);
        final Button previous = viewExamFragment.findViewById(R.id.previous);
        previous.setOnClickListener(this::prevBtn);
        final Button help = viewExamFragment.findViewById(R.id.help);
        help.setOnClickListener(this::helpBtn);
        return viewExamFragment;
    }

    private void nextBtn(View view) {
        final RadioGroup variants = viewExamFragment.findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId() != -1) {
            saveAnswer();
            if (position < store.size() - 1) {
                showAnswer();
                position++;
                fillForm();
            } else {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);
            }
        }
    }

    private void prevBtn(View view) {
        final RadioGroup variants = viewExamFragment.findViewById(R.id.variants);
        if (variants.getCheckedRadioButtonId() != -1) {
            saveAnswer();
            position--;
            fillForm();
        }
    }

    private void helpBtn(View view) {
        Intent intent = new Intent(getActivity(), HintActivity.class);
        intent.putExtra(ExamActivity.HINT_FOR, position);
        startActivity(intent);

    }

    private void saveAnswer() {
        Question question = this.store.get(this.position);
        final RadioGroup variants = viewExamFragment.findViewById(R.id.variants);
        question.setUserAnswer(variants.getCheckedRadioButtonId());
    }

    private void showAnswer() {
        final RadioGroup variants = viewExamFragment.findViewById(R.id.variants);
        int id = variants.getCheckedRadioButtonId();
        Question question = this.store.get(this.position);
        Toast.makeText(
                getContext(), "Your answer is " + id + ", correct is " + question.getAnswer(),
                Toast.LENGTH_SHORT
        ).show();
    }

    private void fillForm() {
        viewExamFragment.findViewById(R.id.previous).setEnabled(position != 0);
        final TextView text = viewExamFragment.findViewById(R.id.question);
        final Question question = this.store.get(this.position);
        text.setText(question.getText());
        final RadioGroup variants = viewExamFragment.findViewById(R.id.variants);
        for (int index = 0; index != variants.getChildCount(); index++) {
            RadioButton button = (RadioButton) variants.getChildAt(index);
            Option option = question.getOptions().get(index);
            button.setId(option.getId());
            button.setText(option.getText());
        }
        variants.check(question.getUserAnswer());
    }
}
