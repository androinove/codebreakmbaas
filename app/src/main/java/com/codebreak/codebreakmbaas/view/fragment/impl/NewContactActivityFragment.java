package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.util.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewContactActivityFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.circle_image_view_contact) CircleImageView mCircleImageViewContactPicture;

    private View mView;
    private File mContactPicture;

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

    @OnClick({R.id.button_take_picture})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_take_picture:
                takePicture();
                break;
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, Constants.REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, String.valueOf(System.currentTimeMillis()), null);
        return Uri.parse(path);
    }

    public String getRealPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
