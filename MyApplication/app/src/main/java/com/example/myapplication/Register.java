package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText sname,sphone,inputEmail,inputPassword,inputpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sname = findViewById(R.id.sname);
        sphone = findViewById(R.id.sphone);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputpa = findViewById(R.id.inputPa);
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void Register(View view) {
        final String n = sname.getText().toString();
        String p =sphone.getText().toString();
        String email =inputEmail.getText().toString();
        String password =inputPassword.getText().toString();
        String s =inputpa.getText().toString();

        if(TextUtils.isEmpty(n)||TextUtils.isEmpty(p)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)||TextUtils.isEmpty(s))
        {
            Toast.makeText(this, "Please fill all the deatils", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length()<6){
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals(s)){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(Register.this, "signed done", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Register.this, "Signed in failed", Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }

    }
}