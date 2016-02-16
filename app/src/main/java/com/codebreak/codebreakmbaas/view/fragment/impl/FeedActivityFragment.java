package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codebreak.codebreakmbaas.R;

import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedActivityFragment extends Fragment {

    private View mView;

    public FeedActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(FeedActivityFragment.this, this.mView);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
