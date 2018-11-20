package com.example.mxo.develtask;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mxo.develtask.listeners.OnLoadMoreListener;
import com.example.mxo.develtask.network.DevelTaskClient;
import com.example.mxo.develtask.network.IDevelTaskService;
import com.example.mxo.develtask.response_data.ChildModel;
import com.example.mxo.develtask.response_data.ParentModel;

import java.net.ConnectException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {

    private IDevelTaskService mService;

    private RelativeLayout rlLoader;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private ArrayList<ChildModel> mChildModels;
    private ArrayList<ChildModel> childModels;
    private AdapterUserListRecyclerView adapterClass;
    private Handler handler;

    public UserListActivity() {
        mService = DevelTaskClient.getClient().create(IDevelTaskService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //if app runs on tablet set orientation landscape
        if(getResources().getBoolean(R.bool.isTablet)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_user_list);

        initViews();
        getDataFromServer();

    }

    private void initViews() {
        rlLoader = findViewById(R.id.progress);
        mRecyclerView = findViewById(R.id.rvUserList);
        mContext = this;
    }


    private void getDataFromServer() {
        rlLoader.setVisibility(View.VISIBLE);

        mService.getParent().enqueue(new Callback<ParentModel>() {
            @Override
            public void onResponse(@NonNull Call<ParentModel> call, @NonNull Response<ParentModel> response) {
                if(!response.isSuccessful() || response.body() == null){
                    Log.d("TAGO", "Response NOT successful");
                    Toast.makeText(UserListActivity.this, "Response NOT successful", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParentModel parentModel = response.body();
                loadData(parentModel);
                int totalItemSize = childModels.size();

                handler = new Handler();
                //setting up adapter
                mRecyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                mRecyclerView.setLayoutManager(layoutManager);
                adapterClass = new AdapterUserListRecyclerView(mChildModels, mRecyclerView, totalItemSize, (UserListActivity) mContext);
                mRecyclerView.setAdapter(adapterClass);

                rlLoader.setVisibility(View.GONE);


                if (childModels.isEmpty()) {
                    mRecyclerView.setVisibility(View.GONE);

                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
                //loading more two users if user scrolled list
                adapterClass.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        //add null , so the adapter will check view_type and show progress bar at bottom
                        mChildModels.add(null);
                        adapterClass.notifyItemInserted(mChildModels.size() - 1);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //   remove progress user
                                mChildModels.remove(mChildModels.size() - 1);
                                adapterClass.notifyItemRemoved(mChildModels.size());
                                //add two users
                                int start = mChildModels.size()-1;
                                int end = start + 2;

                                for (int i = start + 1; i <= end; i++) {
                                    mChildModels.add(childModels.get(i));
                                    adapterClass.notifyItemInserted(mChildModels.size());
                                }
                                adapterClass.setLoaded();
                            }
                        }, 2000);
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<ParentModel> call, @NonNull Throwable t) {
                rlLoader.setVisibility(View.GONE);
                if(t instanceof ConnectException){
                    Toast.makeText(UserListActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserListActivity.this, "No response from server", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadData(ParentModel parentModel) {
        childModels = parentModel.getResults();
        mChildModels = new ArrayList<>();
        //puting in list two default users
        mChildModels.add(childModels.get(0));
        mChildModels.add(childModels.get(1));
    }
}
