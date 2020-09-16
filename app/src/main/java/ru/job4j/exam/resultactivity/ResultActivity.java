package ru.job4j.exam.resultactivity;


import androidx.fragment.app.Fragment;
import ru.job4j.exam.BaseActivity;


public class ResultActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new ResultFragment();
    }
}
