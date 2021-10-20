package com.example.testing;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testing.Models.Results;
import com.example.testing.clients.RetrofitClient;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ListView superListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superListView = findViewById(R.id.superListView);

        getSuperHeroes();


    }

    void getSuperHeroes()
    {
        Call<List<Results>> call= RetrofitClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                List heroism=response.body();
                String[] Herolist=new String[heroism.size()];
                for(int i=0;i<heroism.size();i++)
                {
                    //change it according to  your need
                    Herolist[i]=heroism.get(i).getClass().getName();;
                }
                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, Herolist));
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }


}