package com.example.mypresentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    private EditText firstNameEdittext;
    private  EditText lastNameEdittext;
    private  EditText emailEdittext;
    private  EditText phoneNumberEdittext;
    private Button saveButton;
    private  Button showDataButtons;



    private  String firstName,lastName,phoneNumber,email;
    private  DataHandeler dataHandeler;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseReference= FirebaseDatabase.getInstance().getReference("UserData");

        firstNameEdittext=findViewById(R.id.nameEdittextid);
        lastNameEdittext=findViewById(R.id.lastnameEdittextid);
        emailEdittext=findViewById(R.id.emailEdittextid);
        phoneNumberEdittext=findViewById(R.id.phoneNumberEdittextid);
        saveButton=findViewById(R.id.saveButtonid);
        showDataButtons=findViewById(R.id.showDataButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName=firstNameEdittext.getText().toString();
                lastName=lastNameEdittext.getText().toString();
                email=emailEdittext.getText().toString();
                phoneNumber=phoneNumberEdittext.getText().toString();

                dataHandeler=new DataHandeler(firstName,lastName,email,phoneNumber);

                    databaseReference.child(databaseReference.push().getKey()).setValue(dataHandeler).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Data Inserted Successfull", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
        showDataButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DataShowerActivity.class);
                startActivity(intent);
            }
        });






    }
}
