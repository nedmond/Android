package com.eddu.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.eddu.travelapp.adapter.TravelDealAdapter;

import static com.eddu.travelapp.FirebaseUtil.RC_SIGN_IN;

public class TravelList extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TravelDealAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private static String TAG = TravelList.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        getSupportActionBar().setTitle("Travel Deals");


        FirebaseUtil.openFirebaseReference("traveldeals", this);

        recyclerView = (RecyclerView) findViewById(R.id.travel_recyclerview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        loadDeals();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadDeals();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);

            }
        });


    }

    @SuppressLint("WrongConstant")
    private void loadDeals() {
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TravelDealAdapter(this);
        recyclerView.setAdapter(adapter);
        if (recyclerView == null){
            swipeRefreshLayout.setRefreshing(true);
        }
        else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.i(TAG, "onActivityResult: called");
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    @Override
    protected void onPause() {
        recyclerView.clearOnChildAttachStateChangeListeners();
        FirebaseUtil.removeListener();
        Log.i(TAG, "onPause Called: ");
        super.onPause();
    }

    @Override
    protected void onResume() {
        loadDeals();
        FirebaseUtil.attachListener();
        Log.i(TAG, "onResume Called: ");
        super.onResume();
    }

    private void logout(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseUtil.attachListener();
                        Log.i(TAG, "User logged out");
                    }
                });
        FirebaseUtil.removeListener();
    }

    public void showMenu(){
        invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);

        MenuItem addMenu = menu.findItem(R.id.action_add_deal);
        if (FirebaseUtil.isAdmin == true){
            addMenu.setVisible(true);
        }else {
            addMenu.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_logout) {
            logout();
            return true;
        } else if(id == R.id.action_add_deal){
            startActivity(new Intent(getApplicationContext(), AddTravelDeal.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
