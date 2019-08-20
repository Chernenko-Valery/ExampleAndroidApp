package com.example.loader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static final String LOADER_TAG = "loader";
    private static final int LOADER_ID = 1;
    private Loader<String> mLoader;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.randText);
        Button button = findViewById(R.id.loaderButton);
        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, new Bundle(), this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoader.onContentChanged();
            }
        });
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<String> mLoader = null;
        // условие можно убрать, если вы используете только один загрузчик
        if (id == LOADER_ID) {
            mLoader = new RandomLoader(this);
            Log.d(LOADER_TAG, "onCreateLoader");
        }
        return mLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d(LOADER_TAG, "onLoadFinished");
        textView.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(LOADER_TAG, "onLoaderReset");
    }
}

class RandomLoader extends AsyncTaskLoader<String> {

    private static final int MAX_LENGTH = 15;
    private static final String ALL_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    Random random = new Random();

    public RandomLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Log.d(MainActivity.LOADER_TAG, "onStartLoading");
        super.onStartLoading();
    }

    @Override
    protected void onStopLoading() {
        Log.d(MainActivity.LOADER_TAG, "onStopLoading");
        super.onStopLoading();
    }

    @Override
    public void deliverResult(@Nullable String data) {
        Log.d(MainActivity.LOADER_TAG, "deliverResult");
        super.deliverResult(data);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        SystemClock.sleep(1000);
        Log.d(MainActivity.LOADER_TAG, "loadInBackground");
        return getRandString();
    }

    private String getRandString() {
        char[] text = new char[MAX_LENGTH];
        for (int i = 0; i < MAX_LENGTH; i++)
        {
            text[i] = ALL_SYMBOLS.charAt(random.nextInt(ALL_SYMBOLS.length()));
        }
        return new String(text);
    }
}
