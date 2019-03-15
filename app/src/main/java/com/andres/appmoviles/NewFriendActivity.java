package com.andres.appmoviles;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.andres.appmoviles.model.Friend;

import java.util.UUID;

public class NewFriendActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etAge;
    private EditText etEmail;
    private EditText etPhone;

    private Button btnSaveFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);

        btnSaveFriend = findViewById(R.id.btn_save_friend);
        btnSaveFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Friend friend = new Friend(UUID.randomUUID().toString(), etName.getText().toString(), etAge.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString());
                // Agregar amigo a DB local

            }
        });
    }

    // Cargar información
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age", "");
        String phone = sharedPreferences.getString("phone", "");
        String email = sharedPreferences.getString("email", "");

        etName.setText(name);
        etAge.setText(age);
        etPhone.setText(phone);
        etEmail.setText(email);
    }

    // Guardar información
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putString("name", etName.getText().toString())
                .putString("age", etAge.getText().toString())
                .putString("phone", etPhone.getText().toString())
                .putString("email", etEmail.getText().toString())
                .apply();
    }
}
