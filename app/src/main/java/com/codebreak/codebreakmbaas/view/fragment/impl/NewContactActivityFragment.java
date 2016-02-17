package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.presenter.IContactPresenter;
import com.codebreak.codebreakmbaas.presenter.impl.ContactPresenter;
import com.codebreak.codebreakmbaas.util.Constants;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;
import com.codebreak.codebreakmbaas.view.fragment.IContactView;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewContactActivityFragment extends Fragment implements View.OnClickListener, IContactView {

    @Bind(R.id.relative_layout_new_contact) RelativeLayout mRelativeLayoutNewContact;
    @Bind(R.id.relative_layout_contact_data) RelativeLayout mRelativeLayoutNewContactData;
    @Bind(R.id.relative_layout_loading) RelativeLayout mRelativeLayoutLoading;
    @Bind(R.id.circle_image_view_contact) CircleImageView mCircleImageViewContactPicture;
    @Bind(R.id.edit_text_contact_name) EditText mEditTextName;
    @Bind(R.id.edit_text_contact_phone_number) EditText mEditTextPhoneNumber;

    private View mView;
    private IContactPresenter mIContactPresenter;
    private File mContactPicture;

    public NewContactActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_new_contact, container, false);
        ButterKnife.bind(NewContactActivityFragment.this, this.mView);
        mIContactPresenter = new ContactPresenter(NewContactActivityFragment.this);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.button_take_picture, R.id.button_save_contact})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_take_picture:
                takePicture();
                break;
            case R.id.button_save_contact:
                this.mIContactPresenter.saveContact(getNewContact());
                break;
        }
    }

    private ParseObject getNewContact() {
        ParseObject newContact = new ParseObject("Contact");
        newContact.put("name", mEditTextName.getText().toString());
        newContact.put("phoneNumber", mEditTextPhoneNumber.getText().toString());
        if (this.mContactPicture != null) {
            newContact.put("imageFile", new ParseFile(mContactPicture));
        }
        newContact.put("user", ParseUser.getCurrentUser());
        return newContact;
    }

    private void takePicture() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                startCameraIntent();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_CODE);
            }
        } else {
            startCameraIntent();
        }
    }

    private void startCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, Constants.REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCameraIntent();
                } else {
                    GenericSnackbar.showSnackbar(getActivity(), this.mView, "É necessário permissão de escrita", Snackbar.LENGTH_LONG);
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case Constants.REQUEST_CODE:
                switch (resultCode) {
                    case AppCompatActivity.RESULT_OK:
                        mContactPicture = new File(getRealPath(getImageUri(getActivity(), (Bitmap) data.getExtras().get("data"))));
                        this.mCircleImageViewContactPicture.setImageURI(getImageUri(getActivity(), (Bitmap) data.getExtras().get("data")));
                        break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Uri getImageUri(Context context, Bitmap bitmap) {
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, String.valueOf(System.currentTimeMillis()), null);
        return Uri.parse(path);
    }

    public String getRealPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void showContactsOnUI(List<ParseObject> contacts) {

    }

    @Override
    public void showRootLayout() {
        this.mRelativeLayoutNewContact.setVisibility(View.VISIBLE);
        this.mRelativeLayoutNewContactData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootLayout() {
        this.mRelativeLayoutNewContact.setVisibility(View.GONE);
        this.mRelativeLayoutNewContactData.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingLayout() {
        this.mRelativeLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingLayout() {
        this.mRelativeLayoutLoading.setVisibility(View.GONE);
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
        getFragmentActivity().setResult(AppCompatActivity.RESULT_OK);
        getFragmentActivity().finish();
    }

    @Override
    public void showContactDetailActivity() {

    }

    @Override
    public Activity getFragmentActivity() {
        return getActivity();
    }
}
