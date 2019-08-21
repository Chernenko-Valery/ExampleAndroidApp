package com.example.asynctask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ProgressFragment extends Fragment {

    private static final String PROGRESS_TAG = "progress";
    private static final String FLAG_TAG = "flag";

    ProgressBar progressBar;
    TextView textView;
    MyTask mt;
    boolean isWork = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.progressText);
        if(savedInstanceState!=null) {
            progressBar.setProgress(savedInstanceState.getInt(PROGRESS_TAG));
            textView.setText("Progress: " + progressBar.getProgress());
            isWork = savedInstanceState.getBoolean(FLAG_TAG);
        }
        Button button = view.findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask();
            }
        });

        return view;
    }


    public void startTask() {
        if(isWork)
            return;
        mt = new MyTask();
        if(progressBar.getProgress() == 100)
            progressBar.setProgress(0);
        textView.setText("Progress: " + progressBar.getProgress());
        mt.execute(progressBar.getProgress());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(PROGRESS_TAG, progressBar.getProgress());
        outState.putBoolean(FLAG_TAG, isWork);
        super.onSaveInstanceState(outState);
    }

    class MyTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            isWork = true;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            isWork = false;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            textView.setText("Progress: " + values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            isWork = false;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            for(int i = 0; i <= 100; i+=10) {
                publishProgress(i);
                SystemClock.sleep(1000);
            }
            return null;
        }
    }
}
