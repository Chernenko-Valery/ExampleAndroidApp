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

    private boolean isWork = false;
    private static final String WORKER_FLAG_TAG = "isWork";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        Button button = view.findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
            isWork = savedInstanceState.getBoolean(WORKER_FLAG_TAG);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(WORKER_FLAG_TAG, isWork);
        super.onSaveInstanceState(outState);
    }

    public void startTask() {
        if(!isWork) {
            ProgressBar progressBar = getView().findViewById(R.id.progressBar);
            MyTask task = new MyTask();
            if(progressBar.getProgress() == 100)
                progressBar.setProgress(0);
            TextView textView = getView().findViewById(R.id.progressText);
            textView.setText("Progress: " + progressBar.getProgress());
            task.execute(progressBar.getProgress());
        }
    }



    class MyTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            isWork = true;
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            isWork = false;
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            ProgressBar progressBar = getView().findViewById(R.id.progressBar);
            progressBar.setProgress(values[0]);
            TextView textView = getView().findViewById(R.id.progressText);
            textView.setText("Progress: " + values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            isWork = false;
            super.onCancelled();
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
