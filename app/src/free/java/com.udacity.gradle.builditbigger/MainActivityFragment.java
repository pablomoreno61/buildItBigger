package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

import es.ibrands.jokedisplay.DisplayJokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{
    public String jokeText = null;
    public boolean testFlag = false;
    private PublisherInterstitialAd mPublisherInterstitialAd = null;

    public MainActivityFragment()
    {
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        mPublisherInterstitialAd = new PublisherInterstitialAd(getContext());
        mPublisherInterstitialAd.setAdUnitId("ca-app-pub-5030279640469213/7953474815");

        mPublisherInterstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded()
            {
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int errorCode)
            {
                super.onAdFailedToLoad(errorCode);
                loadAdInterstitial();
            }

            @Override
            public void onAdClosed()
            {
                super.onAdClosed();

                tellJoke();

                loadAdInterstitial();
            }
        });

        loadAdInterstitial();

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        Button button = (Button) root.findViewById(R.id.tellJokeButton);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mPublisherInterstitialAd.isLoaded()) {
                    mPublisherInterstitialAd.show();
                } else {
                    tellJoke();
                }
            }
        });

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    private void tellJoke()
    {
        new AsyncTaskJokeList().execute(this);
    }

    public void startDisplayJokeActivity()
    {
        if (testFlag) {
            return;
        }

        Context context = getActivity();
        Intent intent = new Intent(context, DisplayJokeActivity.class);
        intent.putExtra(DisplayJokeActivity.JOKE_TEXT, jokeText);

        context.startActivity(intent);
    }

    private void loadAdInterstitial()
    {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }
}
