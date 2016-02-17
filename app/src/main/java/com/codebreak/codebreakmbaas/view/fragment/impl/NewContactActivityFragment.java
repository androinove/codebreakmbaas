package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codebreak.codebreakmbaas.R;

import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewContactActivityFragment extends Fragment {

    private View mView;

    public NewContactActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_new_contact, container, false);
        ButterKnife.bind(NewContactActivityFragment.this, this.mView);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
