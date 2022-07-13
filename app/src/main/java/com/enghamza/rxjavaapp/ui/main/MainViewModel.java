package com.enghamza.rxjavaapp.ui.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.enghamza.rxjavaapp.data.api.PostClient;
import com.enghamza.rxjavaapp.model.Post;

import java.util.List;

import io.reactivex.Observable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";
   private MutableLiveData<List<Post>>mutableLiveData=new MutableLiveData<>();

    public  void  observePosts(){

        Observable <List<Post>>observable=PostClient.getINSTANCE().getPosts();

        Observer<List<Post>> observer=new Observer<List<Post>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Post> value) {
                mutableLiveData.setValue(value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: "+e.toString());
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

        .subscribe(observer);
        //Or
       // .subscribe(value->mutableLiveData.setValue(value),error->mutableLiveData.setValue(null));

    }
    public  MutableLiveData<List<Post>> getPosts(){
        return  mutableLiveData;
    }
}
