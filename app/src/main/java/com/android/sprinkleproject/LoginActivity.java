package com.android.sprinkleproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Locale;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView appName = (TextView) findViewById(R.id.appName);
        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/WalterTurncoat.ttf");
        appName.setTypeface(mTypeface);

        final Button btnSubmit = (Button) findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSubmit.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
