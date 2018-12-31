package com.example.rohan.docpatient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static String USER_MAIL;
     public static  String USER_ID="userId";
    private Button buttonSignin;
    private TextView textView;
    private EditText editTextEmail,editTextPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    //   public static String USER_ID;
public String id = "str";
  //  public static String USER_MAIL;
    DatabaseReference databaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

Log.v("ghg ","app");
//        databaseUser=FirebaseDatabase.getInstance().getReference("USER");
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null)
        {


            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));



        }


        progressDialog=new ProgressDialog(this);



        buttonSignin =(Button) findViewById(R.id.button_log);
        editTextEmail=(EditText) findViewById(R.id.mailid);
        editTextPassword=(EditText) findViewById(R.id.pass);
        textView=(TextView) findViewById(R.id.signup);
        buttonSignin.setOnClickListener(this);
        textView.setOnClickListener(this);





    }
    private void userLogin()
    {

        final String mail=editTextEmail.getText().toString().trim();
        String pwd=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(mail))
        {
            Toast.makeText(LoginActivity.this,"please enter a mail id",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pwd))
        {
            Toast.makeText(LoginActivity.this,"please enter a password",Toast.LENGTH_SHORT).show();
            return;
        }




        progressDialog.setMessage("signing in...");

        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(mail,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();


                if(task.isSuccessful())
                {

                    finish();
                         startActivity(new Intent(LoginActivity.this,MainActivity.class));

                }
                else
                {

                }


            }
        });


    }




    @Override
    public void onClick(View v) {


        if (v == buttonSignin) {
            userLogin();
        }
        if (v == textView) {
            finish();
            startActivity(new Intent(this, SignUpActivity.class));

        }
    }
}


