package ru.job4j.exam.hintactivity;

import androidx.fragment.app.Fragment;
import ru.job4j.exam.BaseActivity;
import ru.job4j.exam.examactivity.ExamActivity;


public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return HintFragment.of(
                getIntent().getIntExtra(ExamActivity.HINT_FOR, 0)
        );
    }

}