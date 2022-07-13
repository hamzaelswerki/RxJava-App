package com.enghamza.rxjavaapp.data.api;

import com.enghamza.rxjavaapp.model.Post;

import java.util.List;

import io.reactivex.Observable;
 import retrofit2.http.GET;

interface ApiInterface {


    @GET("posts")
    public Observable<List<Post>> getPosts();
}
