package com.example.rohan.docpatient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DocActivity extends AppCompatActivity {
Button bute;
    TextView nametext,destext,spltext;
    ImageView imgProfile;
    ArrayList<profile> listofdes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc);

        DatabaseReference dbref;
        listofdes=new ArrayList<profile>();
        Intent intent=getIntent();
        String s=intent.getStringExtra("val");
        String s1=intent.getStringExtra("nam");
bute=(Button) findViewById(R.id.button);
bute.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i=new Intent(DocActivity.this,LoginActivity.class);

        startActivity(i);
    }
});
        nametext=(TextView) findViewById(R.id.nameq);
        destext=(TextView) findViewById(R.id.description);
        spltext=(TextView) findViewById(R.id.spl);
        imgProfile=(ImageView) findViewById(R.id.profilepicture);
        dbref=FirebaseDatabase.getInstance().getReference().child("Profile").child(s1);

        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profile p=dataSnapshot.getValue(profile.class);
                nametext.setText(p.getName());
                destext.setText(p.getDes());
                spltext.setText(p.getEmail());
                //  Picasso.with(this).load(p.getProfilePic()).into(imgProfile);
                Picasso.get().load(p.getProfilePic()).into(imgProfile);

                Toast.makeText(DocActivity.this,p.getEmail(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
