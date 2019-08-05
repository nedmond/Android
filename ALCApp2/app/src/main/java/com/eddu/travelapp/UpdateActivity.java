package com.eddu.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.eddu.travelapp.model.TravelDeal;

public class UpdateActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference travelReference;
    private EditText etTitle, etDesc, etPrice;
    private Button btnAdd, btnDelete;
    TravelDeal travelDeal;
    private static String TAG = UpdateActivity.class.getSimpleName();
    TravelList travelList;
    AlertDialog.Builder dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseUtil.openFirebaseReference("traveldeals", travelList);

        database = FirebaseUtil.mFirebaseDatabase;
        travelReference = FirebaseUtil.mDatabaseReference;

        etTitle = findViewById(R.id.update_travel_title);
        etDesc =  findViewById(R.id.update_travel_description);
        etPrice=  findViewById(R.id.update_travel_price);
        btnAdd =  findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        //Updating the travel deal
        Intent intent = getIntent();
        TravelDeal deals = (TravelDeal) intent.getSerializableExtra("TravelDeal");


        if (deals == null){
            deals = new TravelDeal();
        }
        this.travelDeal = deals;

        etTitle.setText(travelDeal.getTitle());
        etPrice.setText(travelDeal.getPrice());
        etDesc.setText(travelDeal.getDescription());

        getSupportActionBar().setTitle("Update " + travelDeal.getTitle());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTravelDeal();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(view.getContext());
                dialog.setMessage("Are sure you want to delete this travel deal?");
                dialog.setTitle("Warning");

                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteDeal();
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialog.show();
            }
        });

        chekAdmin();
    }


    private void chekAdmin() {
        if (FirebaseUtil.isAdmin){
            btnAdd.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            enableEditText(true);
        } else {
            btnAdd.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
            enableEditText(false);
        }
    }

    private void enableEditText(boolean isEnabled){
        etPrice.setEnabled(isEnabled);
        etDesc.setEnabled(isEnabled);
        etTitle.setEnabled(isEnabled);
    }


    private void updateTravelDeal() {
        travelDeal.setTitle( etTitle.getText().toString());
        travelDeal.setDescription( etDesc.getText().toString());
        travelDeal.setPrice(etPrice.getText().toString());
        if (travelDeal.getId() == null){
            travelReference.push().setValue(travelDeal);
        }else{
            travelReference.child(travelDeal.getId()).setValue(travelDeal);
        }

        Toast.makeText(getApplicationContext(), "Travel Deal updated", Toast.LENGTH_SHORT).show();
        finish();
    }



    private void deleteDeal(){
        travelReference.child(travelDeal.getId()).removeValue();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =  item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
