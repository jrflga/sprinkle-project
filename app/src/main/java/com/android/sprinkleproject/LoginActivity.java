package com.android.sprinkleproject;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnSubmit = (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
