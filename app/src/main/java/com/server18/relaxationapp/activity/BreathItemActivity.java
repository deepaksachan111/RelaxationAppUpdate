package com.server18.relaxationapp.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.server18.relaxationapp.R;

public class BreathItemActivity extends AppCompatActivity {
  private Toolbar toolbar_braeth;
    private TextView txt_breath_categary;

    // Declare variables
    ProgressDialog pDialog;
    VideoView videoview;

    // Insert your Video URL
    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_item);
        toolbar_braeth = (Toolbar) findViewById(R.id.toolbar_breath_item);
        setSupportActionBar(toolbar_braeth);

        txt_breath_categary=(TextView)findViewById(R.id.txt_breath_categary);

        String getData = getIntent().getStringExtra("item");
        txt_breath_categary.setText(getData);


        videoview = (VideoView) findViewById(R.id.VideoView);
        // Execute StreamVideo AsyncTask

        // Create a progressbar
        pDialog = new ProgressDialog(BreathItemActivity.this);
        // Set progressbar title
        pDialog.setTitle("Technique First");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();
        techinique1();

    }






    private void techinique1(){
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(
                    BreathItemActivity.this);
            mediacontroller.setAnchorView(videoview);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoview.setMediaController(mediacontroller);
            videoview.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoview.requestFocus();
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoview.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_breath_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
