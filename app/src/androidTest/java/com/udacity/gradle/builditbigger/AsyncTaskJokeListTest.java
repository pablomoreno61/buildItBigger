package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.res.TypedArrayUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import es.ibrands.jokeprovider.JokeProvider;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncTaskJokeListTest
{
    @Test
    public void testDoInBackground() throws Exception
    {
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        mainActivityFragment.testFlag = true;
        new AsyncTaskJokeList().execute(mainActivityFragment);
        Thread.sleep(5000);

        assertNotNull(mainActivityFragment.jokeText);
        JokeProvider jokeProvider = new JokeProvider();
        assertTrue(
            Arrays.asList(jokeProvider.getJokeList()).contains(mainActivityFragment.jokeText)
        );
    }
}
