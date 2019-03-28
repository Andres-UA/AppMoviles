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

    private EditText et_singup_correo;
    private EditText et_singup_pass, et_singup_repass;
    private Button btn_singup_singup;
    private TextView txt_login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        auth = FirebaseAuth.getInstance();

        et_singup_correo = findViewById(R.id.et_signup_correo);
        et_singup_pass = findViewById(R.id.et_signup_pass);
        et_singup_repass = findViewById(R.id.et_signup_repass);
        btn_singup_singup = findViewById(R.id.btn_signup_singup);
        txt_login = findViewById(R.id.txt_login);

        btn_singup_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = et_singup_correo.getText().toString();
                String pass = et_singup_pass.getText().toString();
                String repass = et_singup_repass.getText().toString();

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