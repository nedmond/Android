package com.eddu.travelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.eddu.travelapp.model.TravelDeal;

public class AddTravelDeal extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference travelReference;
    private EditText etTitle, etDesc, etPrice;
    private Button btnAdd, btnImage;
    private static final int PICTURE_RESULT = 55;
    TravelDeal travelDeal;
    TravelList travelList;
    Uri saveUri;
    private static String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_travel_deal);
        getSupportActionBar().setTitle("Add Travel Deal");

        FirebaseUtil.openFirebaseReference("traveldeals", travelList);

        database = FirebaseUtil.mFirebaseDatabase;
        travelReference = FirebaseUtil.mDatabaseReference;

        etTitle = findViewById(R.id.travel_title);
        etDesc = findViewById(R.id.travel_description);
        etPrice = findViewById(R.id.travel_price);
        btnAdd = findViewById(R.id.btn_save);
        btnImage = findViewById(R.id.btn_image);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(intent.createChooser(intent,
                        "Insert Picture"), PICTURE_RESULT);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDeal();
            }
        });

    }


    private void clean() {
        etTitle.setText("");
        etDesc.setText("");
        etPrice.setText("");
        etTitle.requestFocus();
    }

    private void saveDeal() {
        if (saveUri != null) {
            final StorageReference ref = FirebaseUtil.mStorageReference.child(saveUri.getLastPathSegment());

            ref.putFile(saveUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            String mTitle = etTitle.getText().toString();
                            String mDesc = etDesc.getText().toString();
                            String mPrice = etPrice.getText().toString();
                            if (mTitle.isEmpty()) {
                                etTitle.setError("Required");
                                etTitle.requestFocus();


                            } else if (mDesc.isEmpty()) {
                                etDesc.setError("Required");
                                etDesc.requestFocus();
                            } else if (mPrice.isEmpty()) {
                                etPrice.setError("Required");
                                etPrice.requestFocus();
                            } else {
                                travelDeal = new TravelDeal();
                                travelDeal.setImage(url);
                                travelDeal.setDescription(mDesc);
                                travelDeal.setPrice(mPrice);
                                travelDeal.setTitle(mTitle);

                                travelReference.push().setValue(travelDeal);
                                Toast.makeText(getApplicationContext(), "Travel deal added!", Toast.LENGTH_SHORT).show();
                                clean();
                                finish();
                            }

                        }
                    });

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            saveUri = data.getData();
        }
    }
}
