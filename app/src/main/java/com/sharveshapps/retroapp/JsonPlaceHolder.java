package com.sharveshapps.retroapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JsonPlaceHolder {

    @Headers({
            "x-rapidapi-key: f3863348f8mshcf88979928d309bp1a3831jsnef642438a5bd",
            "x-rapidapi-host: moviesdatabase.p.rapidapi.com"
    })
    @GET("titles")
    Call<ApiResponse> getTitles();
}
