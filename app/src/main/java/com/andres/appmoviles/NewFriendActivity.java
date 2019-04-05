package com.andres.appmoviles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.andres.appmoviles.db.DBHandler;
import com.andres.appmoviles.model.Friend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class NewFriendActivity extends AppCompatActivity {

    private static final int CAMERA_CALLBACK_ID = 100;
    private EditText etName;
    private EditText etAge;
    private EditText etEmail;
    private EditText etPhone;
    private ImageView ivPicture;

    private Button btnSaveFriend;
    private Button btnTakePic;

    private File photoFile;

    FirebaseDatabase rtdb;
    FirebaseAuth auth;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

        rtdb = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        // Si no pongo esto entonces va a salir el URIFileExposedException
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        db = DBHandler.getInstance(this);

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        ivPicture = findViewById(R.id.iv_image_friend);

        btnSaveFriend = findViewById(R.id.btn_save_friend);
        btnSaveFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Friend friend = new Friend(UUID.randomUUID().toString(), etName.getText().toString(), etAge.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString());

                // Agregar amigo a DB local
                db.createFriend(friend);

                // Agregar amigo en Firebase
                String uid = auth.getCurrentUser().getUid();
                rtdb.getReference().child("friends").child(uid).push().setValue(friend);

                finish();

            }
        });

        btnTakePic = findViewById(R.id.btn_take_pic);
        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoFile = new File(Environment.getExternalStorageDirectory() + "/" + UUID.randomUUID().toString() + ".png");
                //Uri uri = Uri.fromFile(photoFile);
                Uri uri = FileProvider.getUriForFile(NewFriendActivity.this, getPackageName(), photoFile);
                i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(i, CAMERA_CALLBACK_ID);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Luego de tomar la foto y guardarla
        if (requestCode == CAMERA_CALLBACK_ID && resultCode == RESULT_OK) {
            Bitmap imagen = BitmapFactory.decodeFile(photoFile.toString());
            ivPicture.setImageBitmap(imagen);
        }
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
