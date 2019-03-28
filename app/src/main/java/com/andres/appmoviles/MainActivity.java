package com.andres.appmoviles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.andres.appmoviles.db.DBHandler;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button btnNewFriend;
    private RecyclerView rvFriendsList;

    DBHandler localdb;
    private FriendsListApdapter adapterAmigos;
    FirebaseAuth auth;
    private Button btn_signout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localdb = DBHandler.getInstance(this);
        auth = FirebaseAuth.getInstance();


        //Si no hay usuario loggeado
        if (auth.getCurrentUser() == null) {

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();

            return;
        }

        btn_signout = findViewById(R.id.btn_signout);
        btnNewFriend = findViewById(R.id.btn_add_new_friend);
        rvFriendsList = findViewById(R.id.rv_friends_list);
        adapterAmigos = new FriendsListApdapter();
        rvFriendsList.setLayoutManager(new LinearLayoutManager(this));
        rvFriendsList.setAdapter(adapterAmigos);
        rvFriendsList.setHasFixedSize(true);

        btnNewFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewFriendActivity.class);
                startActivity(intent);
            }
        });

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterAmigos.showAllFriends(localdb.getAllFriend());
    }
}
