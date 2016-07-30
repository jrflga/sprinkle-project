package com.android.sprinkleproject;

import android.app.Activity;
import android.app.ActivityOptions;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Random;

public class LoginActivity extends Activity {

    Button btnSubmit;
    LinearLayout loggingBar;
    LinearLayout loginContent;
    LinearLayout socialButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSubmit = (Button) findViewById(R.id.submit);
        loggingBar = (LinearLayout) findViewById(R.id.logging_bar);
        loginContent = (LinearLayout) findViewById(R.id.login_content);
        socialButtons = (LinearLayout) findViewById(R.id.social_btns);

        TextView appName = (TextView) findViewById(R.id.appName);
        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/Slabo27px.ttf");
        appName.setTypeface(mTypeface);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loginContent.setVisibility(View.GONE);
                socialButtons.setVisibility(View.GONE);
                loggingBar.setVisibility(View.VISIBLE);

                Intent slideActivity = new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundleAnim = ActivityOptions.makeCustomAnimation(getApplicationContext(),
                                                                        R.anim.trans_left_in,
                                                                        R.anim.trans_left_out)
                                                                        .toBundle();
                startActivity(slideActivity, bundleAnim);
                LoginActivity.this.finish();
            }
        });
    }
}
