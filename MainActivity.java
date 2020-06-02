package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity  {
    //private long backPressedTime=0;

    TextView BarcodeText;
    EditText product_name,price,date;
    Button Scan,Save, ShowRecords;
    FirebaseFirestore db;
   ProgressDialog pd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Scan=findViewById(R.id.scan);
        Save=findViewById(R.id.save);
        BarcodeText=findViewById(R.id.barcode_text);
        ShowRecords = findViewById(R.id.showData);

        product_name=findViewById(R.id.product_name);
        price=findViewById(R.id.product_price);
        date=findViewById(R.id.CD);

        ShowRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(i);

            }
        });


    pd = new ProgressDialog(this);

        Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Scanner.class);
                startActivity(i);
                finish();



            }
        });

        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");

        // display the string into textView
        BarcodeText.setText(str);

        db = FirebaseFirestore.getInstance();
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String barcodeNum=(BarcodeText.getText().toString());
                String productName=(product_name.getText().toString());
                String Price=(price.getText().toString());
                String Date=(date.getText().toString());

                uploadData(barcodeNum,productName,Price,Date);
            }
        });






    }//end of onCreate()..........

    private void uploadData(String barcodeNum, String productName, String Price, String Date) {
        pd.setTitle("Adding product");
        pd.show();

        String id= UUID.randomUUID().toString();

        Map< String, Object > newProduct = new HashMap< >();
        newProduct.put("id",id);
        newProduct.put("Barcode_number",barcodeNum);
        newProduct.put("Product_name",productName);
        newProduct.put("Price",Price);
        newProduct.put("Date",Date);

        db.collection("ProductDetails").document(id).set(newProduct)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "product added...", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }




    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

    }



}

