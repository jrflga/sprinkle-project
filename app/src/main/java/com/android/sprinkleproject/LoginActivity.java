package com.android.sprinkleproject;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends Activity {

    private static final String TAG = "Login";

    public static FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;

    private Button       mSubmit;
    private Button       mRegister;
    private LinearLayout loggingBar;
    private LinearLayout loginContent;
    private LinearLayout socialButtons;
    private TextView     appName;
    private TextView     loggingText;
    private EditText     mEmailField;
    private EditText     mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSubmit =        (Button) findViewById(R.id.submit);
        mRegister =      (Button) findViewById(R.id.register);
        loggingBar =     (LinearLayout) findViewById(R.id.logging_bar);
        loginContent =   (LinearLayout) findViewById(R.id.login_content);
        socialButtons =  (LinearLayout) findViewById(R.id.social_btns);
        appName =        (TextView) findViewById(R.id.appName);
        loggingText =    (TextView) findViewById(R.id.logging_text);
        mEmailField =    (EditText) findViewById(R.id.emailLabel);
        mPasswordField = (EditText) findViewById(R.id.passwordLabel);

        mAuth = FirebaseAuth.getInstance();

       mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                updateUI(user);
            }
        };

        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/Slabo27px.ttf");
        appName.setTypeface(mTypeface);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        loginContent.setVisibility(View.GONE);
        socialButtons.setVisibility(View.GONE);
        loggingBar.setVisibility(View.VISIBLE);
        loggingText.setText(R.string.registering_action);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            loginContent.setVisibility(View.VISIBLE);
                            socialButtons.setVisibility(View.VISIBLE);
                            loggingBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, R.string.regis_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        //hideProgressDialog();
                    }
                });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();
        loginContent.setVisibility(View.GONE);
        socialButtons.setVisibility(View.GONE);
        loggingBar.setVisibility(View.VISIBLE);
        loggingText.setText(R.string.logging_action);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        if (!task.isSuccessful()) {
                            loginContent.setVisibility(View.VISIBLE);
                            socialButtons.setVisibility(View.VISIBLE);
                            loggingBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        //hideProgressDialog();
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_error_edittext));
            valid = false;
        } else {
            mEmailField.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edittext));
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_error_edittext));
            valid = false;
        } else {
            mPasswordField.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edittext));
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user != null) {
            Intent slideActivity = new Intent(LoginActivity.this, MainActivity.class);
            Bundle bundleAnim = ActivityOptions.makeCustomAnimation(getApplicationContext(),
                    R.anim.trans_left_in,
                    R.anim.trans_left_out)
                    .toBundle();
            startActivity(slideActivity, bundleAnim);
            LoginActivity.this.finish();
        } else {
            //USER NOT LOGGED
        }
    }

}
