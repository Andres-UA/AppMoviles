package com.andres.appmoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNewFriend;
    private RecyclerView rvFriendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewFriend = findViewById(R.id.btn_add_new_friend);
        rvFriendsList = findViewById(R.id.rv_friends_list);

        btnNewFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewFriendActivity.class);
                startActivity(intent);
            }
        });
    }
}
