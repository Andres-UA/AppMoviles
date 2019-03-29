package com.andres.appmoviles;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingupActivity extends AppCompatActivity {

    private EditText et_singup_email;
    private EditText et_singup_password;
    private EditText et_singup_repassword;
    private Button btn_singup;
    private TextView txt_login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        auth = FirebaseAuth.getInstance();

        et_singup_email = findViewById(R.id.et_signup_email);
        et_singup_password = findViewById(R.id.et_signup_password);
        et_singup_repassword = findViewById(R.id.et_signup_repassword);
        btn_singup = findViewById(R.id.btn_signup);
        txt_login = findViewById(R.id.txt_login);

        btn_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = et_singup_email.getText().toString();
                String pass = et_singup_password.getText().toString();
                String repass = et_singup_repassword.getText().toString();

                if (pass.equals(repass)) {
                    auth.createUserWithEmailAndPassword(correo, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent i = new Intent(SingupActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SingupActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SingupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}