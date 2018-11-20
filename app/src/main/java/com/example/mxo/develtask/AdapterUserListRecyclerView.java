package com.example.mxo.develtask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mxo.develtask.listeners.OnLoadMoreListener;
import com.example.mxo.develtask.response_data.ChildModel;
import com.example.mxo.develtask.response_data.MyImageViewTarget;

import java.util.ArrayList;

import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_AVATAR_URL;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_FULLNAME;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_LOCATION;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_PHONE;
import static com.example.mxo.develtask.utils.Constants.VIEW_ITEM;
import static com.example.mxo.develtask.utils.Constants.VIEW_PROG;

public class AdapterUserListRecyclerView extends RecyclerView.Adapter {

    private ArrayList<ChildModel> mUsersList;
    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private UserListActivity mParent;

    AdapterUserListRecyclerView(ArrayList<ChildModel> users, RecyclerView recyclerView, final int totalSize, UserListActivity parent) {
        this.mUsersList = users;
        mParent = parent;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (mUsersList.size() == totalSize){
                        // End has been reached
                        loading = true;
                    }
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mUsersList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_content, parent, false);
            vh = new UserViewHolder(v);

        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progressbar, parent, false);
            vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {

            ChildModel childModel = mUsersList.get(position);
            ((UserViewHolder) holder).tvFirstName.setText(childModel.getName().getFirst());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int selectedPosition = holder.getLayoutPosition();
                    if (view.getResources().getBoolean(R.bool.isTablet)) {
                        Bundle arguments = new Bundle();
                        arguments.putString(ARG_PARAM_AVATAR_URL, mUsersList.get(selectedPosition).getPicture().getLargePictureUrl());
                        arguments.putString(ARG_PARAM_FULLNAME, mUsersList.get(selectedPosition).getName().getFullName());
                        arguments.putString(ARG_PARAM_LOCATION, mUsersList.get(selectedPosition).getLocation().getWhereabouts());
                        arguments.putString(ARG_PARAM_PHONE, mUsersList.get(selectedPosition).getPhone());
                        UserDetailFragment fragment = new UserDetailFragment();
                        fragment.setArguments(arguments);
                        mParent.getSupportFragmentManager().beginTransaction().replace(R.id.user_detail_container, fragment).commit();

                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, UserDetailActivity.class);
                        intent.putExtra(ARG_PARAM_AVATAR_URL, mUsersList.get(selectedPosition).getPicture().getMediumPictureUrl());
                        intent.putExtra(ARG_PARAM_FULLNAME, mUsersList.get(selectedPosition).getName().getFullName());
                        intent.putExtra(ARG_PARAM_LOCATION, mUsersList.get(selectedPosition).getLocation().getWhereabouts());
                        intent.putExtra(ARG_PARAM_PHONE, mUsersList.get(selectedPosition).getPhone());
                        context.startActivity(intent);
                    }
                }
            });

            MyImageViewTarget backTarget = new MyImageViewTarget(((UserViewHolder) holder).ivAvatar);
            Glide.with(holder.itemView).load(childModel.getPicture().getLargePictureUrl()).into(backTarget);


        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    void setLoaded() {
        loading = false;
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstName;
        ImageView ivAvatar;
        LinearLayout mBackground;

        UserViewHolder(final View v) {
            super(v);
            tvFirstName = v.findViewById(R.id.tvFirstName);
            ivAvatar = v.findViewById(R.id.ivAvatar);
        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }
}