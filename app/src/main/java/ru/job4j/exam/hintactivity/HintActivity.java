package ru.job4j.exam.hintactivity;

import androidx.fragment.app.Fragment;
import ru.job4j.exam.BaseActivity;


public class HintActivity extends BaseActivity {

    @Override
    public Fragment loadFrg() {
        return new HintFragment();
    }

}