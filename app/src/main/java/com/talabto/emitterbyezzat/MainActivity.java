package com.talabto.emitterbyezzat;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.talabto.emitterbyezzat.adapters.RecUserAdapter;
import com.talabto.emitterbyezzat.model.UserModel;
import com.talabto.emitterbyezzat.network.UserClient;
import com.talabto.emitterbyezzat.services.MyForeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // ui
    private RecyclerView rec_user;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AlertDialog alertDialog;

    // var
    private UserClient userClient = UserClient.getInstance();
    private List<UserModel> userModels = new ArrayList<>();
    private RecUserAdapter recUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkServiceIsRun();
        init();

        actions();


        if (getIntent().getAction()!=null)
        {
            if (getIntent().getAction().equals("show_res"))
            {
                String res =getIntent().getStringExtra("response");

                showRecivedDialog(res);
            }
        }


    }
    public void cancelServices(View view) {
        stopService(new Intent(getApplicationContext(), MyForeService.class));
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    void checkServiceIsRun() {
        if (!isMyServiceRunning(MyForeService.class)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(new Intent(getApplicationContext(), MyForeService.class));
            } else {
                startService(new Intent(getApplicationContext(), MyForeService.class));
            }

        }

    }

    private void actions() {
        recUserAdapter.setOnUserItemClick(new RecUserAdapter.OnUserItemClick() {
            @Override
            public void onClick(int pos) {
                showConrimDialog(pos);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });
    }

    private void showRecivedDialog(String res) {
        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("reciver response")
                .setMessage("the reciver response is "+res)
                .create();
        alertDialog.show();
    }
    private void showConrimDialog(int pos) {
        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setMessage("Confirm to send data to middle man app")

                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("ddddddddddd", "onClick: " + userModels.get(pos).toString());
                        Intent intent=new Intent("com.talabto.emitterbyezzat.recive");

                        intent.putExtra("user",userModels.get(pos).toString());
                        sendBroadcast(intent);

                    }
                }).create();
        alertDialog.show();
    }

    private void init() {

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);


        rec_user = findViewById(R.id.rec_user);
        rec_user.setHasFixedSize(true);
        rec_user.setLayoutManager(new LinearLayoutManager(this));

        recUserAdapter = new RecUserAdapter();
        rec_user.setAdapter(recUserAdapter);
        fetchData();

    }

    void fetchData() {
        swipeRefreshLayout.setRefreshing(true);
        userClient.getUsers().enqueue(new Callback<List<UserModel>>() {
            @SuppressLint("NewApi")
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                userModels = response.body();
                recUserAdapter.setUserModels(userModels);
                swipeRefreshLayout.setRefreshing(false);

            }


            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}