package ru.job4j.exam;

/**
 * Курс Job4j
 * @autor Устюжанин Павел
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static int scrRotationCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        scrRotationCount = (savedInstanceState == null) ? 0: savedInstanceState.getInt("screenRotation") + 1;
        Log.d(TAG, "OnCreate" + "       Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "OnStart" + "        Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "OnResume" + "       Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "OnPause" + "        Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "OnStop" + "         Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onDestroy() {
        //screenRotation++;
        super.onDestroy();
        Log.d(TAG, "OnDestroy" + "      Rotation count - " + scrRotationCount);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("screenRotation", scrRotationCount);
        Log.d(TAG, "onSaveInstanceState" + "    Rotation count - " + scrRotationCount);
    }
}