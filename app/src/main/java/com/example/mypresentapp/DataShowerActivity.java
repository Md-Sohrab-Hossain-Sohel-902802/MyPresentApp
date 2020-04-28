package com.example.mypresentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataShowerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  MyAdapter adapter;
    private List<DataHandeler> dataHandelerList=new ArrayList<>();

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_shower);


        recyclerView=findViewById(R.id.recyclerViewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter=new MyAdapter(DataShowerActivity.this,dataHandelerList);
        recyclerView.setAdapter(adapter);


        adapter.setOnitemclickListener(new MyAdapter.OnItemclickListener() {
            @Override
            public void onItemClick(int position) {
                    DataHandeler selectedItem=dataHandelerList.get(position);

                    String mobilenumber=selectedItem.getPhoneNumber();
                Toast.makeText(DataShowerActivity.this, ""+mobilenumber, Toast.LENGTH_SHORT).show();




            }

            @Override
            public void showFirstName(int position) {
                DataHandeler selectedItem=dataHandelerList.get(position);

                String firstName=selectedItem.getFirstName();
                Toast.makeText(DataShowerActivity.this, ""+firstName, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void showLastName(int position) {
                DataHandeler selectedItem=dataHandelerList.get(position);

                String lastname=selectedItem.getLastName();
                Toast.makeText(DataShowerActivity.this, ""+lastname, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void showEmail(int position) {
                DataHandeler selectedItem=dataHandelerList.get(position);

                String email=selectedItem.getEmail();
                Toast.makeText(DataShowerActivity.this, ""+email, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void showMobile(int position) {
                DataHandeler selectedItem=dataHandelerList.get(position);

                String mobile=selectedItem.getPhoneNumber();
                Toast.makeText(DataShowerActivity.this, ""+mobile, Toast.LENGTH_SHORT).show();

            }

        });





    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseReference= FirebaseDatabase.getInstance().getReference("UserData");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataHandelerList.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    DataHandeler data=dataSnapshot1.getValue(DataHandeler.class);
                    dataHandelerList.add(data);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
}
