package ru.job4j.exam.examactivity;

/**
 * Курс Job4j
 * @autor Устюжанин Павел
 */

import androidx.fragment.app.Fragment;
import ru.job4j.exam.BaseActivity;

public class ExamActivity extends BaseActivity {

    public static final String HINT_FOR = "hint_for";

    @Override
    public Fragment loadFrg() {
        return new  ExamFragment();
    }



}
