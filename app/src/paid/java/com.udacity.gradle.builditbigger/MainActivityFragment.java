package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.udacity.gradle.builditbigger.R;
import es.ibrands.jokedisplay.DisplayJokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    public String jokeText = null;
    public boolean testFlag = false;

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.tellJokeButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                getJoke();
            }
        });

        return root;
    }

    public void getJoke()
    {
        new AsyncTaskJokeList().execute(this);
    }

    public void startDisplayJokeActivity()
    {
        if (!testFlag) {
            Context context = getActivity();
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.JOKE_TEXT, jokeText);

            context.startActivity(intent);
        }
    }
}
