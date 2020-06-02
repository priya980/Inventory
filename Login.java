package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    public EditText log_email, log_pass;
    Button btn_log;
    TextView signup;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        log_email=findViewById(R.id.log_email);
        log_pass=findViewById(R.id.log_pass);
        btn_log=findViewById(R.id.btn_login);
        signup=findViewById(R.id.signin);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user!= null){
                    //Toast.makeText(getApplicationContext(), "user already logged in", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(getApplicationContext(),AdminUser.class);
                    startActivity(i);
                }else{
                    //Toast.makeText(getApplicationContext(), "Continue Login", Toast.LENGTH_SHORT).show();
                }
            }
        };
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail=log_email.getText().toString();
                String userPasswd = log_pass.getText().toString();

                if(userEmail.isEmpty()){
                    log_email.setError("fill email id");
                }else if(userPasswd.isEmpty()){
                    log_pass.setError("fill password");
                }else if(userEmail.isEmpty() && userPasswd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "required fields are empty", Toast.LENGTH_SHORT).show();
                }
                else if(!(userEmail.isEmpty() && userPasswd.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(userEmail,userPasswd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login Unsuccessfull "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), "Login successfull", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),AdminUser.class));
                            }
                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
