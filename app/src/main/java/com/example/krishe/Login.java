package com.example.krishe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://krish-e-fbb5e-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone =findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneTxt = phone.getText().toString();
                final String passwordTxt = password.getText().toString();

                if (phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Login.this, "Please Enter your mobile or password", Toast.LENGTH_SHORT).show();
                }else {
                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                // check if mobile is exist in firebase database
                                if(snapshot.hasChild(phoneTxt)){
                                    // mobile is exist in firebase database
                                    // get password of user from firebase and match it with user entererd pass
                                    
                                    final String getPassword = snapshot.child(phoneTxt).child("password").getValue(String.class);
                                    if(getPassword.equals(passwordTxt)){
                                        Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                        // open MainActivity on Success
                                        startActivity(new Intent(Login.this, Plant.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Login.this, "Wrong Mobile Number", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                }
            }
        });
        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open register activity
                startActivity(new Intent(Login.this, Register.class));
            }
        });

    }
}
