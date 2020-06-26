package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView textView,textView2,textView3;
    FirebaseAuth mAuth;
    DatabaseReference reference ;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        textView= findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference().child("Users").child(mAuth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Pojo pojo = snapshot.getValue(Pojo.class);
                textView.setText(pojo.getName());
                textView2.setText(pojo.getEmail());
                textView3.setText(pojo.getPhone());
            }
////////////////////////////////////it is a comment 
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "LOL", Toast.LENGTH_SHORT).show();
            }
        });
    }





    public void signout(View view) {

        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(),Home.class));

    }

    public void edit(View view) {
        startActivity(new Intent(getApplicationContext(),Update.class));
    }
}