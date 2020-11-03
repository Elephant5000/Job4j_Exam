package ru.job4j.exam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ru.job4j.exam.examactivity.ExamActivity;


public class ExamsActivity extends AppCompatActivity {
    private RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.exams);
        this.recycler = findViewById(R.id.exams);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        updateUI();
    }

    private void updateUI() {
        List<Exam> exams = new ArrayList<Exam>();
        for (int index = 0; index != 10; index++) {
            exams.add(new Exam(index, String.format("Exam %s", index), System.currentTimeMillis(), 0));
        }
        this.recycler.setAdapter(new ExamAdapter(exams));
    }




    public class ExamAdapter extends RecyclerView.Adapter<ExamHolder> {

        private  final List<Exam> exams;

        public ExamAdapter(List<Exam> exams) {
            this.exams = exams;
        }


        @NonNull
        @Override
        public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.info_exam, parent, false);
            return new ExamHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ExamHolder holder, int position) {
            final Exam exam = this.exams.get(position);
            TextView examName = holder.view.findViewById(R.id.info);
            TextView examResult = holder.view.findViewById(R.id.result);
            TextView examDate = holder.view.findViewById(R.id.date);
            DateFormat dateFormat = new SimpleDateFormat("HH:mm dd:MM:yyyy");
            examName.setText(exam.getName());
            examResult.setText(String.valueOf(exam.getResult()));
            examDate.setText((exam.getResult()  != 0) ? dateFormat.format(exam.getTime()) : "Empty");

            examName.setOnClickListener(
                v -> {
                    //Toast.makeText(holder.view.getContext(), "You select " + exam, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ExamsActivity.this, ExamActivity.class);
                    //intent.putExtra(ExamActivity.HINT_FOR, store.getPosition());
                    startActivity(intent);
                }
            );
        }

        @Override
        public int getItemCount() {
            return this.exams.size();
        }
    }




    public static class ExamHolder extends RecyclerView.ViewHolder {

        public View view;

        public ExamHolder(@NonNull View view) {
            super(view);
            this.view = itemView;
        }

    }

}
