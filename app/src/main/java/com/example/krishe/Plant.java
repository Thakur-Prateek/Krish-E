package com.example.krishe;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Plant extends AppCompatActivity {
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReferenceMoisture,databaseReferenceHumidity, databaseReferenceTemper;
        private TextView idHumidity,idMoisture,idTemper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReferenceMoisture =firebaseDatabase.getReference("Moisture");
        databaseReferenceHumidity=firebaseDatabase.getReference("humidity");
        databaseReferenceTemper=firebaseDatabase.getReference("temperature");
        idHumidity =findViewById(R.id.idHumidity);
        idMoisture=findViewById(R.id.idMoisture);
        idTemper=findViewById(R.id.idTemper);
        getdataMoisture();
        getdataHumidity();
        getdataTemper();
    }
    private void getdataTemper(){
        databaseReferenceTemper.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                idTemper.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Plant.this, "Fail to get Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void getdataHumidity(){
        databaseReferenceHumidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                idHumidity.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Plant.this, "Fail to get Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getdataMoisture(){
        databaseReferenceMoisture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                idMoisture.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Plant.this, "Fail to get Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
