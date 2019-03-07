package com.hfad.stopwatch;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 秒表功能
 *
 * @author Administrator
 */
public class StopwatchActivity extends Activity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    private MyTimerTask mTask;
    private TextView mTextView;
    private Disposable mDisposable;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        mTextView = (TextView) findViewById(R.id.time_view);
        mTask = new MyTimerTask();

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        //todo use Handle.post(Runnable,delay)
        //runTimer();
        //todo use timer
        //new Timer().schedule(mTask, 0, 1000);
        //todo use asyncTask
        //new MyAsyncTask(this).execute();
        // todo use rxJava
        //useRxJava();
        //todo use sub_thread
        useSubThread();

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }


    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    /**
     * 使用Handler+Runnable()
     */
    @SuppressWarnings("unused")
    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String time = getShowText();
                mTextView.setText(time);
                handler.postDelayed(this, 1000);
            }
        });

    }

    private String getShowText() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
        if (running) {
            seconds++;
        }
        return time;
    }

    /**
     * 使用Timer+TimerTask+Handler()
     */
    @SuppressWarnings("unused")
    private class MyTimerTask extends TimerTask {
        MyTimerTask() {
        }

        @Override
        public void run() {
            final String time = getShowText();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(time);
                }
            });
        }
    }

    /**
     * 使用不安全的AsyncTask
     */
    @SuppressWarnings("unused")
    private static class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        private static final String TAG = "MyAsyncTask";
        WeakReference<StopwatchActivity> mWeakReference;
        boolean running = true;

        MyAsyncTask(StopwatchActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            final StopwatchActivity activity = this.mWeakReference.get();
            if (activity != null) {

                final String text = activity.getShowText();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.mTextView.setText(text);
                    }
                });
            } else {
                running = false;
                this.cancel(false);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (running) {
                publishProgress(0);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: ", e);
                }
            }
            return null;
        }
    }

    @SuppressWarnings("all")
    private void useRxJava() {
        mDisposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        final String text = getShowText();
                        mTextView.setText(text);
                    }
                });
    }

    @SuppressWarnings("all")
    private void useSubThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final String text = getShowText();
                    //could replace next method with Handle.sendMessage
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(text);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
