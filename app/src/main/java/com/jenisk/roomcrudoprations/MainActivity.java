package com.jenisk.roomcrudoprations;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jenisk.roomcrudoprations.adapter.DirectoryListAdapter;
import com.jenisk.roomcrudoprations.utils.SeparatorDecoration;

/**
 * Created by Jenis Kasundra on 17/05/2018.
 */

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabAddUser;
    DirectoryListAdapter directoryListAdapter;
    private RecyclerView rvDirectory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        addListner();
    }

    private void bindView() {
        rvDirectory = (RecyclerView) findViewById(R.id.rvDirectory);
        rvDirectory.setLayoutManager(new LinearLayoutManager(this));
        SeparatorDecoration decoration = new SeparatorDecoration(this, Color.GRAY, 1.5f);
        rvDirectory.addItemDecoration(decoration);
        fabAddUser = (FloatingActionButton) findViewById(R.id.fabAddUser);
    }

    private void addListner() {
        fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddDirectoryActivity.class));
            }
        });
    }


    /**
     * set recyclerview with try get data from realm
     */
    public void setRecyclerView() {
        directoryListAdapter = new DirectoryListAdapter(MainActivity.this, MyApplication.getInstance().getDB().directoryDao().getAllDirectory());
        rvDirectory.setAdapter(directoryListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setRecyclerView();
    }

}
