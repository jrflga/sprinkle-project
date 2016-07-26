package com.android.sprinkleproject;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class LoginActivity extends Activity implements SurfaceHolder.Callback{

    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mMediaPlayer = new MediaPlayer();
        mSurfaceView = (SurfaceView) findViewById(R.id.surface);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Uri videoURI = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.nature);

        try {
            mMediaPlayer.setDataSource(String.valueOf(videoURI));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get the dimensions of the video
        int videoWidth = mMediaPlayer.getVideoWidth();
        int videoHeight = mMediaPlayer.getVideoHeight();

        //Get the width of the screen
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        //Get the SurfaceView layout parameters
        android.view.ViewGroup.LayoutParams lp = mSurfaceView.getLayoutParams();

        //Set the width of the SurfaceView to the width of the screen
        lp.width = screenWidth;

        //Set the height of the SurfaceView to match the aspect ratio of the video
        //be sure to cast these as floats otherwise the calculation will likely be 0
        lp.height = (int) (((float)videoHeight / (float)videoWidth) * (float)screenWidth);

        //Commit the layout parameters
        mSurfaceView.setLayoutParams(lp);

        //Start video
        mMediaPlayer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
