package ru.job4j.exam.examactivity;

/**
 * Курс Job4j
 * @autor Устюжанин Павел
 */


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.exam.BaseActivity;
import ru.job4j.exam.Exam;
import ru.job4j.exam.R;


public class ExamActivity extends BaseActivity {

    public static final String HINT_FOR = "hint_for";

    @Override
    public Fragment loadFrg() {
        return new ExamFragment();
    }



}

