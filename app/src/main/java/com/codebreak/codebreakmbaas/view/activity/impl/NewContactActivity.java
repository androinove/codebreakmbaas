package com.codebreak.codebreakmbaas.view.activity.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.codebreak.codebreakmbaas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewContactActivity extends AppCompatActivity {

    @Bind(R.id.activity_new_contact_toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        ButterKnife.bind(NewContactActivity.this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.back();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.back();
        super.onBackPressed();
    }

    private void back() {
        setResult(AppCompatActivity.RESULT_OK);
        finish();
    }

}
