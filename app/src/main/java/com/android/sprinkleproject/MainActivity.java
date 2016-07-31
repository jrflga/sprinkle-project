package com.android.sprinkleproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.sprinkleproject.Utils.ZigZagLayout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ScrollView scrollview;
    int notificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ZigZagLayout zigZagLayout = (ZigZagLayout) findViewById(R.id.zzl);
        zigZagLayout.setViewMargin(50);
        zigZagLayout.setLineStrokeWidth(15);
        zigZagLayout.setLineColor(Color.parseColor("#007C38"));

        createButton("1", zigZagLayout);
        createButton("2", zigZagLayout);
        createButton("3", zigZagLayout);
        createButton("4", zigZagLayout);
        createButton("5", zigZagLayout);
        createButton("6", zigZagLayout);
        createButton("7", zigZagLayout);
        createButton("8", zigZagLayout);
        createButton("9", zigZagLayout);
        createButton("10", zigZagLayout);
        createButton("11", zigZagLayout);
        createButton("12", zigZagLayout);
        createButton("13", zigZagLayout);

        scrollview = (ScrollView) findViewById(R.id.mainScrollView);
        scrollview.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(View.FOCUS_DOWN);
            }
        }, 200);


        //notificationID = (int) System.currentTimeMillis();
        notificationID = 1;
        //addNotification();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeNotification();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
