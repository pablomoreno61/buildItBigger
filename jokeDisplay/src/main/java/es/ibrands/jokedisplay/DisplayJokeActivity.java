package es.ibrands.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Android library
 */
public class DisplayJokeActivity extends AppCompatActivity
{
    public static final String JOKE_TEXT = "JOKE_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_joke_activity);

        TextView jokeTextView = findViewById(R.id.joke_text_view);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(DisplayJokeActivity.JOKE_TEXT);

        if (!joke.isEmpty()) {
            jokeTextView.setText(joke);
        }
    }
}
