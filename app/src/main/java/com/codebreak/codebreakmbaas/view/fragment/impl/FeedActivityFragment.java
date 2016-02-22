package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.presenter.IContactPresenter;
import com.codebreak.codebreakmbaas.presenter.IUserPresenter;
import com.codebreak.codebreakmbaas.presenter.impl.ContactPresenter;
import com.codebreak.codebreakmbaas.presenter.impl.UserPresenter;
import com.codebreak.codebreakmbaas.util.Constants;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;
import com.codebreak.codebreakmbaas.view.activity.impl.MainActivity;
import com.codebreak.codebreakmbaas.view.activity.impl.NewContactActivity;
import com.codebreak.codebreakmbaas.view.adapter.ContactRecyclerViewAdapter;
import com.codebreak.codebreakmbaas.view.fragment.IContactView;
import com.codebreak.codebreakmbaas.view.fragment.IFeedView;
import com.codebreak.codebreakmbaas.view.fragment.IUserView;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedActivityFragment extends Fragment implements IFeedView, IContactView, IUserView, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycler_view_feed) RecyclerView mRecyclerViewFeed;
    @Bind(R.id.swipe_refresh_layout_feed) SwipeRefreshLayout mSwipeRefreshLayout;

    private View mView;
    private IUserPresenter mIUserPresenter;
    private IContactPresenter mIContactPresenter;
    private ContactRecyclerViewAdapter mContactRecyclerViewAdapter;
    private int hasInstance = 0;

    public FeedActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(FeedActivityFragment.this, this.mView);
        setHasOptionsMenu(true);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mIUserPresenter = new UserPresenter(FeedActivityFragment.this);
        this.mIContactPresenter = new ContactPresenter(FeedActivityFragment.this);
        setUpSwipeRefreshLayout();
        getContacts();
    }

    @Override
    public void onResume() {
        super.onResume();
        hasInstance++;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (hasInstance > 0) {
            this.mIUserPresenter = new UserPresenter(FeedActivityFragment.this);
            this.mIContactPresenter = new ContactPresenter(FeedActivityFragment.this);
        }
    }

    private void setUpSwipeRefreshLayout() {
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mRecyclerViewFeed.setLayoutManager(new LinearLayoutManager(getFragmentContext(), LinearLayoutManager.VERTICAL, false));
        this.mRecyclerViewFeed.setAdapter(new ContactRecyclerViewAdapter(getFragmentContext(), new ArrayList<ParseObject>()));
    }

    private void getContacts() {
        this.mIContactPresenter.getContacts(this.mIUserPresenter.getCurrentUser());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_feed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick({R.id.floating_action_button_add_contact})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_add_contact:
                startActivityForResult(new Intent(getFragmentContext(), NewContactActivity.class), Constants.REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_CODE:
                switch (resultCode) {
                    case AppCompatActivity.RESULT_OK:
                        this.mContactRecyclerViewAdapter.notifyDataSetChanged();
                        break;
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.mIUserPresenter.signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showContactsOnUI(List<ParseObject> contacts) {
        Log.d(Constants.DEBUG_KEY, "Size -> " + contacts.size());
        this.mContactRecyclerViewAdapter = new ContactRecyclerViewAdapter(getFragmentContext(), contacts);
        this.mRecyclerViewFeed.setLayoutManager(new LinearLayoutManager(getFragmentContext(), LinearLayoutManager.VERTICAL, false));
        this.mRecyclerViewFeed.setAdapter(this.mContactRecyclerViewAdapter);
    }

    @Override
    public void showRefresh() {
        this.mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void hideRefresh() {
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRootLayout() {
        this.mRecyclerViewFeed.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootLayout() {
        this.mRecyclerViewFeed.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void hideLoadingLayout() {

    }

    @Override
    public void showFeedActivity() {

    }

    @Override
    public Activity getFragmentContext() {
        return getActivity();
    }

    @Override
    public void showSnackbarMessage(String message, int duration) {
        GenericSnackbar.showSnackbar(getFragmentActivity(), this.mView, message, duration);
    }

    @Override
    public void showSnackbarMessage(int resId, int duration) {
        GenericSnackbar.showSnackbar(getFragmentActivity(), this.mView, resId, duration);
    }

    @Override
    public void showMainActivity() {
        getFragmentActivity().startActivity(new Intent(getFragmentActivity(), MainActivity.class));
        getFragmentActivity().finish();
    }

    @Override
    public void showSplashScreen() {

    }

    @Override
    public void hideSplashScreen() {

    }

    @Override
    public void showContactDetailActivity() {

    }

    @Override
    public Activity getFragmentActivity() {
        return getActivity();
    }

    @Override
    public void onRefresh() {
        this.mRecyclerViewFeed.setAdapter(null);
        getContacts();
    }
}
