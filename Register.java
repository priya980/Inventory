package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {
    public EditText get_email, get_password,name;
    Button get_signup;
    TextView signIn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth= FirebaseAuth.getInstance();
        get_email=findViewById(R.id.check_email);
        get_password=findViewById(R.id.check_password);
        name=findViewById(R.id.name);
        get_signup=findViewById(R.id.btn_signup);
        signIn=findViewById(R.id.TVSignIn);
        firebaseAuth= FirebaseAuth.getInstance();
//        if(firebaseAuth !=null){
//            Intent i = new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(i);
//            finish();
//        }

        get_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID=get_email.getText().toString();
                String passwd= get_password.getText().toString();

                if(emailID.isEmpty()){
                    get_email.setError("fill email id");
                }else if(passwd.isEmpty()){
                    get_password.setError("fill password");
                }else if(!(emailID.isEmpty() && passwd.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(emailID,passwd).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if(!task.isSuccessful()){
                              Toast.makeText(getApplicationContext(), "Registration Unsuccessfull "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                          }else{
                              Toast.makeText(getApplicationContext(), "registration successfull", Toast.LENGTH_LONG).show();
                              startActivity(new Intent(getApplicationContext(),AdminUser.class));
                          }
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
}
