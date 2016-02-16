package com.codebreak.codebreakmbaas.view.activity.impl;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.activity_feed_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(FeedActivity.this);
        setSupportActionBar(mToolbar);
    }



    @OnClick({R.id.floating_action_button_add_contact})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_add_contact:
                GenericSnackbar.showSnackbar(FeedActivity.this, v, "Teste", Snackbar.LENGTH_LONG);
                break;
        }
    }

}
