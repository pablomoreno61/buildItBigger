package es.ibrands.jokeprovider;

import java.util.ArrayList;
import java.util.Random;

/**
 * Java library
 */
public class JokeProvider
{
    private final String jokeList[] = {
        "First joke",
        "Second joke",
        "Third joke",
        "Fourth joke",
        "Fifth joke"
    };

    public String getRandomJoke()
    {
        Random rand = new Random();
        int value = rand.nextInt(jokeList.length - 1);
        return jokeList[value];
    }

    public String[] getJokeList()
    {
        return jokeList;
    }
}
