package com.android.sprinkleproject;

import android.app.ActivityOptions;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.sprinkleproject.utils.ZigZagLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Home";

    private FloatingActionButton  fab;
    private ScrollView            mScrollview;
    private ZigZagLayout          mZigZagLayout;
    private Toolbar               mToolbar;
    private DrawerLayout          mDrawer;
    private NavigationView        mNavigation;
    private ActionBarDrawerToggle toggle;

    int notificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar =      (Toolbar) findViewById(R.id.toolbar);
        mZigZagLayout = (ZigZagLayout) findViewById(R.id.zzl);
        mScrollview =   (ScrollView) findViewById(R.id.mainScrollView);
        mDrawer =       (DrawerLayout) findViewById(R.id.drawer_layout);
        fab =           (FloatingActionButton) findViewById(R.id.fab);
        mNavigation =   (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(mToolbar);

        mZigZagLayout.setViewMargin(50);
        mZigZagLayout.setLineStrokeWidth(15);
        mZigZagLayout.setLineColor(Color.parseColor("#007C38"));

        for(int i = 0; i < 2; i++) {
            createButton("ID: " + i, mZigZagLayout);
        }

        mScrollview.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScrollview.fullScroll(View.FOCUS_UP);
            }
        }, 200);


        //notificationID = (int) System.currentTimeMillis();
        notificationID = 1;
        //addNotification();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeNotification();
                createButton("ID: ", mZigZagLayout);
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        assert mNavigation != null;
        mNavigation.setNavigationItemSelectedListener(this);
    }

    private void addNotification() {
        Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_logo_wireframe)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentTitle("Orchid #1 needs water!")
                        .setContentText("Don't forget to do so ASAP!")
                        .setAutoCancel(false)
                        .setOngoing(false)  // TODO: Set to true
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_EVENT)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notificationID, builder.build());

        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
    }
    private void removeNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(notificationID);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // TODO: home fragment
        } else if (id == R.id.nav_map) {
            // TODO: map fragment
        } else if (id == R.id.nav_profile) {
            startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        } else if (id == R.id.nav_logout) {
            signOut();
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        LoginActivity.mAuth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void createButton(String text, ZigZagLayout zig) {
        FloatingActionButton _fab = new FloatingActionButton(this);
        _fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_leaf));
        _fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#009945")));
        _fab.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        zig.addView(_fab);
        final OvershootInterpolator interpolator = new OvershootInterpolator();
        ViewCompat.animate(_fab).rotation(180f).withLayer().setDuration(0).setInterpolator(interpolator).start();
        _fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PlantProfileActivity.class));
            }
        });
    }
}
