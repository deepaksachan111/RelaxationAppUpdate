package com.server18.relaxationapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.server18.relaxationapp.activity.ProfileActivity;
import com.server18.relaxationapp.fragment.BreathFragment;
import com.server18.relaxationapp.fragment.PhotosFragment;
import com.server18.relaxationapp.fragment.SoundFragment;
import com.server18.relaxationapp.fragment.WordsFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout notificationCount1;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawerPane;


    private ImageView imageView_home_bottom,iv_home_menudrawer;

    private TextView _bottom_breath,bottom_sounds,bottom_photos,bottom_words  ,tv_home_profile;

    MenuItem menusitem;

     TextView badge_notification_1;
  int count[] = new int[]{11,21,333,42,5,6,7,8,9,10,11,12,13,14};
    int i = 1;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_home_frag, new HomeFragment()).commit();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //  showslide();
        findIDS();


        mDrawerPane = (LinearLayout) findViewById(R.id.drawerPane);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                //   getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                // getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };



    }


    private void findIDS() {
        imageView_home_bottom = (ImageView) findViewById(R.id.back_home_bottom_icon);
        iv_home_menudrawer = (ImageView) findViewById(R.id.iv_home_menudrawer);
        _bottom_breath =(TextView)findViewById(R.id.bottom_breath_tv);
        bottom_sounds  =(TextView)findViewById(R.id.bottom_sounds_tv);
        bottom_photos = (TextView)findViewById(R.id.bottom_photos_tv);
        bottom_words =(TextView)findViewById(R.id.bottom_words_tv);
        tv_home_profile=(TextView)findViewById(R.id.tv_home_profile);

        imageView_home_bottom.setOnClickListener(this);
        iv_home_menudrawer.setOnClickListener(this);
        _bottom_breath.setOnClickListener(this);
        bottom_sounds.setOnClickListener(this);
        bottom_photos.setOnClickListener(this);
        bottom_words.setOnClickListener(this);
        tv_home_profile.setOnClickListener(this);

    }


    private void popupStart(){
    /*    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("How to close alertdialog programmatically");
        builder.setMessage("5 second dialog will close automatically");
        builder.setCancelable(true);

        final AlertDialog closedialog= builder.create();

        closedialog.show();*/


      final Dialog dialogPopup  = new Dialog(this,R.style.DialogSlideAnim);
        dialogPopup.setCancelable(true);

        dialogPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialogPopup.setContentView(R.layout.custom_popup_dialog);

        TextView text = (TextView) dialogPopup.findViewById(R.id.text_dialog);


         dialogPopup.getWindow().setGravity(Gravity.TOP);

        dialogPopup.show();
    }


/*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);




       MenuItem item1 = menu.findItem(R.id.notifiction);
       MenuItemCompat.setActionView(item1, R.layout.notification_update_count_layout);
       notificationCount1 = (RelativeLayout) MenuItemCompat.getActionView(item1);

       badge_notification_1 =(TextView)notificationCount1.findViewById(R.id.badge_notification_1);
        final Handler tipsHanlder = new Handler();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for( i = 0 ;i<100; i++){

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        tipsHanlder.post(new Runnable() {
                            @Override
                            public void run() {
                                badge_notification_1.setText(i +"");
                            }
                        });
                    }

                }
            }).start();


        Button notification =(Button)notificationCount1.findViewById(R.id.button1);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EventBookAudioActivity.class));
                Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_LONG).show();
            }
        });

        return true;
    }*/



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.notifiction) {

            //Toast.makeText(getApplicationContext(), "show item on cart working progress", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.custum_activity) {

           // popupStart();
          //  invalidateOptionsMenu();
          //  startActivity(new Intent(getApplicationContext(), EventBookAudioActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }


/*   @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
  boolean canAddItem = false;
        if(canAddItem){
            menusitem=   menu.getItem(1).setIcon(android.R.drawable.ic_popup_sync);
          *//*  MenuItem mi = menu.add("New Item");
            mi.setIcon(R.drawable.ic_location_web_site);
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);*//*
            canAddItem = false;
        }
        else{
            menu.getItem(1).setIcon(R.mipmap.show_all);
            canAddItem = true;
        }

        return super.onPrepareOptionsMenu(menu);
    }*/


    @Override
    public void onClick(View v) {

        if(v == imageView_home_bottom){
            bottom_photos.setTextColor(Color.WHITE);
            bottom_words.setTextColor(Color.WHITE);
            bottom_sounds.setTextColor(Color.WHITE);
            _bottom_breath.setTextColor(Color.WHITE);
            Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag, new HomeFragment()).addToBackStack(null).commit();
        }else if(v ==_bottom_breath){
            bottom_photos.setTextColor(Color.WHITE);
            bottom_words.setTextColor(Color.WHITE);
            _bottom_breath.setTextColor(Color.RED);
            bottom_sounds.setTextColor(Color.WHITE);
            Toast.makeText(getApplicationContext(), "Breathe", Toast.LENGTH_LONG).show();
          // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag, new BreathFragment()).addToBackStack(null).commit();
            BreathFragment breathFragment = BreathFragment.newInstance(1,"Breath");

         getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag, breathFragment).commit();

        }else if(v == bottom_sounds){
            bottom_photos.setTextColor(Color.WHITE);
            bottom_words.setTextColor(Color.WHITE);
            _bottom_breath.setTextColor(Color.WHITE);
            bottom_sounds.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(), "Sounds", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag, new SoundFragment()).commit();
        }else if(v == bottom_photos){
            bottom_words.setTextColor(Color.WHITE);
            bottom_sounds.setTextColor(Color.WHITE);
            _bottom_breath.setTextColor(Color.WHITE);
            bottom_photos.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(), "photos", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag, new PhotosFragment()).commit();
        }
        else if(v == bottom_words){
            bottom_photos.setTextColor(Color.WHITE);
            bottom_sounds.setTextColor(Color.WHITE);
            _bottom_breath.setTextColor(Color.WHITE);
            bottom_words.setTextColor(Color.RED);
            Toast.makeText(getApplicationContext(), "words", Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag, new WordsFragment()).commit();
        } else if(v == tv_home_profile){
             mDrawerLayout.closeDrawer(mDrawerPane);
            Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_LONG).show();
           startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
