package com.example.mxo.develtask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_AVATAR_URL;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_FULLNAME;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_LOCATION;
import static com.example.mxo.develtask.utils.Constants.ARG_PARAM_PHONE;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(ARG_PARAM_FULLNAME, getIntent().getStringExtra(ARG_PARAM_FULLNAME));
            arguments.putString(ARG_PARAM_AVATAR_URL, getIntent().getStringExtra(ARG_PARAM_AVATAR_URL));
            arguments.putString(ARG_PARAM_LOCATION, getIntent().getStringExtra(ARG_PARAM_LOCATION));
            arguments.putString(ARG_PARAM_PHONE, getIntent().getStringExtra(ARG_PARAM_PHONE));

            UserDetailFragment fragment = new UserDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.user_detail_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, UserListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
