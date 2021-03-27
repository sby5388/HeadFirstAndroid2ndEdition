package com.hfad.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener {
    public static final String TAG = "chap09";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        Log.d(TAG, "itemClicked: id = " + id);
        final Intent intent = DetailActivity.newIntent(this, (int) id);
        startActivity(intent);
    }
}
