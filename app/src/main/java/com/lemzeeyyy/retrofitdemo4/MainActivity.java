package com.lemzeeyyy.retrofitdemo4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieAPI movieAPI = retrofit.create(MovieAPI.class);
        Call<Response> call = movieAPI.getMovies();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Response jsonResponse = response.body();
                movies = jsonResponse.getMovie();
                loadDataToRecyclerView(movies);

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }
    private void loadDataToRecyclerView(List<Movie> movies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        movieAdapter = new MovieAdapter(getApplicationContext(),movies);
        recyclerView.setAdapter(movieAdapter);
    }
}