package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import es.ibrands.jokeprovider.JokeProvider;

/** An endpoint class we are exposing */
@Api(
    name = "myApi",
    version = "v1",
    namespace = @ApiNamespace(
        ownerDomain = "backend.builditbigger.gradle.udacity.com",
        ownerName = "backend.builditbigger.gradle.udacity.com",
        packagePath = ""
    )
)

public class MyEndpoint
{
    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean myBean = new MyBean();
        myBean.setData("Hello " + name);

        return myBean;
    }

    @ApiMethod(name = "getRandomJoke")
    public MyBean getRandomJoke()
    {
        MyBean myBean = new MyBean();
        JokeProvider jokeProvider = new JokeProvider();
        myBean.setData(jokeProvider.getRandomJoke());

        return myBean;
    }
}
