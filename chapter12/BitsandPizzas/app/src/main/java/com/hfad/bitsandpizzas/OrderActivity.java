package com.hfad.bitsandpizzas;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    public static final String TAG = "OrderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickDone(View view) {
        CharSequence text = "Your order has been updated";
        int duration = Snackbar.LENGTH_SHORT;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        // TODO: 2021/3/18 设置
        final View snackbarView = snackbar.getView();
        final int minimumWidth = snackbarView.getMinimumWidth();
        Log.d(TAG, "onClickDone: minimumWidth = " + minimumWidth);
        snackbarView.setMinimumWidth(800);
        snackbar.setAction("Undo",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(OrderActivity.this, "Undone!",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
        snackbar.show();
    }
}
