package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

class AsyncTaskJokeList extends AsyncTask<MainActivityFragment, Void, String>
{
    private static MyApi myApi = null;

    private MainActivityFragment mMainActivityFragment;

    @Override
    protected String doInBackground(MainActivityFragment... params)
    {
        mMainActivityFragment = params[0];

        if (myApi == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null
                )
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://localhost:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer()
                {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException
                    {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
            // end options for devappserver

            myApi = builder.build();
        }

        try {
            return myApi.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return "API ERROR: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result)
    {
        mMainActivityFragment.jokeText = result;
        mMainActivityFragment.startDisplayJokeActivity();
    }
}
