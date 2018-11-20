package com.example.mxo.develtask;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mxo.develtask.response_data.MyImageViewTarget;

import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_AVATAR_URL;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_FULLNAME;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_LOCATION;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_PHONE;

public class UserDetailFragment extends Fragment {


    public UserDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_PARAM_FULLNAME) &&
                                      getArguments().containsKey(ARG_PARAM_AVATAR_URL) &&
                                      getArguments().containsKey(ARG_PARAM_LOCATION) &&
                                      getArguments().containsKey(ARG_PARAM_PHONE)) {

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = null;
            if (activity != null) {
                appBarLayout = activity.findViewById(R.id.toolbar_layout);
            }
            if (appBarLayout != null) {
                appBarLayout.setTitle(getArguments().getString(ARG_PARAM_FULLNAME));
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_detail, container, false);
        ImageView ivAvatar = rootView.findViewById(R.id.ivPhoto);

        if (getArguments() != null) {
            ((TextView) rootView.findViewById(R.id.tvFullName)).setText(getArguments().getString(ARG_PARAM_FULLNAME));
            ((TextView) rootView.findViewById(R.id.tvUserLocation)).setText(getArguments().getString(ARG_PARAM_LOCATION));
            ((TextView) rootView.findViewById(R.id.tvPhoneNumber)).setText(getArguments().getString(ARG_PARAM_PHONE));

            MyImageViewTarget backTarget = new MyImageViewTarget(ivAvatar);
            Glide.with(rootView).load(getArguments().getString(ARG_PARAM_AVATAR_URL)).into(backTarget);
        }

        return rootView;
    }
}
