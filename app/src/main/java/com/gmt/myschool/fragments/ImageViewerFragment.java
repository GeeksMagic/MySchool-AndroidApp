package com.gmt.myschool.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gmt.myschool.R;
import com.gmt.myschool.activity.MainActivity;

/**
 * Created by user on 6/25/2016.
 */
public class ImageViewerFragment extends SuperFragment {

    private ImageView mImageView;
    private Button mCancel, mUpload;

    @Override
    public String getTitle() {
        return "Profile Pic";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_viewer, container, false);
        mImageView = (ImageView) view.findViewById(R.id.image);
        mCancel = (Button) view.findViewById(R.id.cancel);
        mUpload = (Button) view.findViewById(R.id.upload);
        ((MainActivity) getActivity()).updateProfilePicInstance(mImageView);

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }
}
