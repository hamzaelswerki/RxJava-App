package com.enghamza.rxjavaapp.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.enghamza.rxjavaapp.R;
import com.enghamza.rxjavaapp.model.Post;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainViewModel viewModel;
    RecyclerView recyclerView;
    PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();
        initRecycler();
        getPosts();

    }

    private void getPosts() {
        viewModel.getPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                    adapter.setList(posts);
            }
        });
    }

    private void initViewModel() {
         viewModel =new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.observePosts();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler);
        adapter = new PostsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}