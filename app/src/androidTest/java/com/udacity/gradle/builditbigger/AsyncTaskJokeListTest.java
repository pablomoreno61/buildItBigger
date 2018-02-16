package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
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
    }
}
