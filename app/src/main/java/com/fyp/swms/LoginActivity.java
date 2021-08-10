package com.fyp.swms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 156;


    GoogleSignInClient mGoogleSignInClient;

    SignInButton signInButton;

    ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init(this);

        signInButton = findViewById(R.id.sign_in_button);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    public void init(Context context){
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completeTask) {
        try {
            GoogleSignInAccount acc = completeTask.getResult(ApiException.class);
            Toast.makeText(this, "Sign in successfully", Toast.LENGTH_SHORT).show();
            updateUI();
        } catch (ApiException e) {
            Toast.makeText(this, "Failed to sign in", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null) {
            signInButton.setVisibility(View.GONE);
            findViewById(R.id.layoutProfile).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.tvFirstName)).setText(account.getGivenName());
            ((TextView) findViewById(R.id.tvLastName)).setText(account.getFamilyName());
            ((TextView) findViewById(R.id.tvEmail)).setText(account.getEmail());
            Picasso.with(this).load(account.getPhotoUrl()).into(ivProfileImage);
        } else {
            Toast.makeText(this, "Account is null", Toast.LENGTH_SHORT).show();
        }
    }

    public void signOut(Context context) {
        init(context);
        mGoogleSignInClient.signOut();
        Toast.makeText(context, "You are Logged out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


    public void continueClicked(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }


}