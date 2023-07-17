package com.example.hosplinictracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity implements View.OnClickListener {

GoogleSignInClient mGoogleSignInClient;
String name, email;
private CardView d1,d2,d3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        d1= (CardView) findViewById(R.id.d1);
        d2= (CardView) findViewById(R.id.d2);
        d3= (CardView) findViewById(R.id.d3);



        d1.setOnClickListener((View.OnClickListener) this);
        d2.setOnClickListener((View.OnClickListener) this);
        d3.setOnClickListener((View.OnClickListener) this);



        TextView username = (TextView) findViewById(R.id.username);
        TextView useremail = (TextView) findViewById(R.id.useremail);

       name = getIntent().getStringExtra("Name");
       email = getIntent().getStringExtra("Email");

        username.setText(name);
        useremail.setText(email);

        Button signout = findViewById(R.id.sign_out);
        signout.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.sign_out:
                signOut();
                break;

            case R.id.d1:
                 i = new Intent(this, CurrentMap.class);
                startActivity(i);
                break;

            case R.id.d2:
                i = new Intent(this, MapsActivity.class);
                startActivity(i);
                break;
            case R.id.d3:
                i = new Intent(this, AboutUs.class);
                startActivity(i);
                break;


        }

    }



    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), email + " signed out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}