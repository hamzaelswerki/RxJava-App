package com.enghamza.rxjavaapp.data.api;

import com.enghamza.rxjavaapp.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private ApiInterface apiInterface;
    private static PostClient INSTANCE;


     private PostClient(){
         Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .build();

         apiInterface =retrofit.create(ApiInterface.class);
     }

    public static PostClient getINSTANCE() {
         if (INSTANCE==null)
             INSTANCE=new PostClient();

        return INSTANCE;
    }

    public Observable<List<Post>> getPosts(){
         return apiInterface.getPosts();
    }

}
