//package com.sharveshapps.retroapp;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class MainActivity extends AppCompatActivity {
//    private JsonPlaceHolder jsonPlaceHolderApi;
//    private TextView textViewResult;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        textViewResult = findViewById(R.id.text_view_result);
//
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://moviesdatabase.p.rapidapi.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient)
//                .build();
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);
//        getTitles();
//    }
//
//    private void getTitles() {
//        Call<ApiResponse> movieCall = jsonPlaceHolderApi.getTitles();
//        movieCall.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                if (!response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
//                }
//
//                ApiResponse apiResponse = response.body();
//                List<Movie> movies = apiResponse.getResults();
//
//                for (Movie movie : movies) {
//                    String content = "";
//                    content += "ID: " + movie.getId() + "\n";
//
//                    TitleText titleText = movie.getTitleText();
//                    if (titleText != null) {
//                        content += "Title: " + titleText.getText() + "\n";
//                    } else {
//                        content += "Title: N/A\n";
//                    }
//
//                    PrimaryImage primaryImage = movie.getPrimaryImage();
//                    if (primaryImage != null) {
//                        content += "Primary Image URL: " + primaryImage.getUrl() + "\n\n";
//                    } else {
//                        content += "Primary Image URL: N/A\n\n";
//                    }
//
//                    textViewResult.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
//    }
//}
package com.sharveshapps.retroapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sharveshapps.retroapp.ApiResponse;
import com.sharveshapps.retroapp.JsonPlaceHolder;
import com.sharveshapps.retroapp.Movie;
import com.sharveshapps.retroapp.MovieAdapter;
import com.sharveshapps.retroapp.R;

import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private JsonPlaceHolder jsonPlaceHolderApi;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieAdapter = new MovieAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(movieAdapter);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://moviesdatabase.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolder.class);
        getTitles();
    }

    private void getTitles() {
        Call<ApiResponse> movieCall = jsonPlaceHolderApi.getTitles();
        movieCall.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,""+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }

                ApiResponse apiResponse = response.body();
                List<Movie> movies = apiResponse.getResults();

                // Update the RecyclerView with the movie data
                movieAdapter.setMovieList(movies);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,""+t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
