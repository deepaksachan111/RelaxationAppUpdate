package com.server18.relaxationapp.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.server18.relaxationapp.R;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeditationAlamFragment extends Fragment {
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;

    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;
    private TextView meditation_frag_starttime;
    MediaPlayer mp;

    Typeface typeface;

    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();;

    private SeekBar seekbar;
    private TextView tx1,tx2;

    public static int oneTimeOnly = 0;

    private Button btn_play,btn_stop;
    MediaPlayer mediaPlayer;
    private EditText editText_setTime;
    private ImageView   ic_menu;
    private boolean flag = true;
    private  Uri uri;


    public MeditationAlamFragment() {
        // Required empty public constructor




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meditation_alam, container, false);
        //   AnalogClock analog = (AnalogClock)v. findViewById(R.id.analog_clock);
        meditation_frag_starttime = (TextView)v.findViewById(R.id.meditation_frag_starttime);
        editText_setTime= (EditText)v.findViewById(R.id.edt_time_getvalue);

        ic_menu =(ImageView)v.findViewById(R.id.ic_menu);
        ic_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMenu();
            }
        });


        final Button btnStart = (Button)v. findViewById(R.id.btn_start);
        final Button btnPause = (Button)v. findViewById(R.id.btn_pause);
        final Button btnResume = (Button)v. findViewById(R.id.btn_resume);
     /*   btnStart.setEnabled(false);
          editText_setTime.addTextChangedListener(new TextWatcher() {
              @Override
              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                  btnStart.setEnabled(true);
              }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {

              }

              @Override
              public void afterTextChanged(Editable s) {

              }
          });*/

        //Initially disabled the pause, resume and cancel button
        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        //Set a Click Listener for start button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPaused = false;
                isCanceled = false;



                if (editText_setTime.getText().toString().equals("")) {
                    Toast.makeText(getActivity(),"Please Enter Time in Mintue",Toast.LENGTH_LONG).show();
                    btnPause.setVisibility(View.GONE);
                    btnResume.setEnabled(false);
                } else {
                    //Disable the start and pause button
                    btnStart.setEnabled(false);
                    btnStart.setVisibility(View.GONE);
                    btnPause.setVisibility(View.VISIBLE);
                    btnResume.setEnabled(false);
                    //Enabled the pause and cancel button
                    btnPause.setEnabled(true);
                    int vvalue= Integer.parseInt(editText_setTime.getText().toString());
                    editText_setTime.setEnabled(false);
                    CountDownTimer timer;
                    long millisInFuture = 1000 * 60 *vvalue; //10 seconds
                    long countDownInterval = 1000; //1 second


                    //Initialize a new CountDownTimer instance
                    timer = new CountDownTimer(millisInFuture, countDownInterval) {
                        public void onTick(long millisUntilFinished) {
                            //do something in every tick
                            if (isPaused || isCanceled) {
                                //If the user request to cancel or paused the
                                //CountDownTimer we will cancel the current instance
                                cancel();
                            } else {

                                //1 second = 1000 milliseconds
                                //  meditation_frag_starttime.setText("" + millisUntilFinished / 1000);

                                // timeRemaining = millisUntilFinished;

                                int seconds = (int) (millisUntilFinished / 1000);
                                int minutes = seconds / 60;
                                seconds = seconds % 60;
                                meditation_frag_starttime.setText("" + String.format("%02d", minutes)
                                        + ":" + String.format("%02d", seconds));

                                timeRemaining = millisUntilFinished;
                            }
                        }


                        public void onFinish() {
                            //Do something when count down finished
                            meditation_frag_starttime.setText("Done");

                            //Enable the start button
                            btnStart.setEnabled(true);
                            //Disable the pause, resume and cancel button
                            btnPause.setEnabled(false);
                            btnResume.setEnabled(false);
                            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.t_bowl);
                            mediaPlayer.start();

                        }


                    }.start();

                }
            }
        });

        //Set a Click Listener for pause button
        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //When user request to pause the CountDownTimer
                isPaused = true;

                //Enable the resume and cancel button
                btnResume.setEnabled(true);

                //Disable the start and pause button
                btnStart.setEnabled(false);
                btnPause.setEnabled(false);

                btnStart.setVisibility(View.GONE);
                btnPause.setVisibility(View.GONE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });

        //Set a Click Listener for resume button
        btnResume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Disable the start and resume button
                btnStart.setEnabled(false);
                btnResume.setEnabled(false);
                //Enable the pause and cancel button
                btnPause.setEnabled(true);
                btnPause.setVisibility(View.VISIBLE);
                btnResume.setVisibility(View.GONE);

                //Specify the current state is not paused and canceled.
                isPaused = false;
                isCanceled = false;

                //Initialize a new CountDownTimer instance
                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval){
                    public void onTick(long millisUntilFinished){
                        //Do something in every tick
                        if(isPaused || isCanceled)
                        {
                            //If user requested to pause or cancel the count down timer
                            cancel();
                        }
                        else {

                            int seconds = (int) (millisUntilFinished / 1000);
                            int minutes = seconds / 60;
                            seconds = seconds % 60;
                            meditation_frag_starttime.setText("" + String.format("%02d", minutes)
                                    + ":" + String.format("%02d", seconds));
                            // meditation_frag_starttime.setText("" + millisUntilFinished / 1000);
                            //Put count down timer remaining time in a variable
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        //Do something when count down finished
                        meditation_frag_starttime.setText("Done");
                        //Disable the pause, resume and cancel button
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.t_bowl);
                        mediaPlayer.start();
                        //Enable the start button
                        btnStart.setEnabled(true);
                    }
                }.start();

            }
        });


        // reverseTimer(600,meditation_frag_starttime);
        typeface  = Typeface.createFromAsset(getActivity().getAssets(), "digital7.ttf");
        meditation_frag_starttime.setTypeface(typeface);
        mp = new MediaPlayer();


     /*   try{
            //you can change the path, here path is external directory(e.g. sdcard) /Music/maine.mp3
          //  mp.setDataSource(Environment.getExternalStorageDirectory().getPath()+"/Music/maine.mp3");
            mp = MediaPlayer.create(getActivity(), R.raw.titennium);
            Toast.makeText(getActivity(),"Music playing please wait",Toast.LENGTH_LONG).show();
            mp.start();
        }catch(Exception e){e.printStackTrace();}*/




        btn_play =(Button)v.findViewById(R.id.btn_music_play);
        btn_stop =(Button)v.findViewById(R.id.btn_music_stop);


        tx1=(TextView)v.findViewById(R.id.textView2);
        tx2=(TextView)v.findViewById(R.id.textView3);

        mediaPlayer = new MediaPlayer();
        //  String s = getIntent().getStringExtra("S");

        seekbar=(SeekBar)v.findViewById(R.id.seekBar);
        seekbar.setClickable(false);


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag) {
                    mp = MediaPlayer.create(getActivity(), R.raw.titennium);

                }else{
                    mp = MediaPlayer.create(getActivity(), uri);
                }
                Toast.makeText(getActivity(), "Playing sound", Toast.LENGTH_SHORT).show();
                mp.start();

                finalTime = mp.getDuration();
                startTime = mp.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }
                tx2.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
                );

                tx1.setText(String.format("%d min, %d sec",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime)))
                );

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
                btn_play.setEnabled(false);
                btn_stop.setEnabled(true);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
               /* oneTimeOnly = 0;
                myHandler.removeCallbacks(UpdateSongTime);
                seekbar.setMax(0);*/
                btn_stop.setEnabled(false);
                btn_play.setEnabled(true);
            }
        });



        return v;
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mp.getCurrentPosition();
            tx1.setText(String.format("%d min, %d sec",

                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekbar.setProgress((int)startTime);
            myHandler.postDelayed(this, 100);
        }
    };





 /*   public void reverseTimer(final int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000,1000) {

            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText("TIME : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {

                mp.stop();
                tv.setText("Completed");


            }
        }.start();
    }*/

    @Override
    public void onPause() {
        mp.stop();
        mediaPlayer.stop();
        super.onPause();
        oneTimeOnly =0;
        myHandler.removeCallbacks(UpdateSongTime);
        seekbar.setMax(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }



    private void addMenu() {

        final Dialog openDialog = new Dialog(getActivity());
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.custumdialoge_popupmenu);

        LinearLayout linear_profile=(LinearLayout)openDialog.findViewById(R.id.linear_menu_user);
        LinearLayout linear_changepassword=(LinearLayout)openDialog.findViewById(R.id.linear_menu_password);
        final LinearLayout linear_logout=(LinearLayout)openDialog.findViewById(R.id.linear_menu_logout);

        linear_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // startActivity(new Intent(getApplicationContext(),MyProfile.class));
                openDialog.dismiss();
            }
        });

        linear_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("audio/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Audio "), 1);
                openDialog.dismiss();
                flag = false;
                mp.stop();
                btn_stop.setEnabled(false);
                btn_play.setEnabled(true);
            }
        });

        linear_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog.dismiss();
            }
        });

        // AlertDialog alertDialog = openDialog.create();
        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        //  window.showAsDropDown(edt_custom_sharing);
        window.setGravity(Gravity.RIGHT | Gravity.TOP);
        //openDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        openDialog.getWindow().setBackgroundDrawableResource(R.color.translucent_black);


        openDialog.getWindow().getAttributes().verticalMargin = 0.08F;
        openDialog.getWindow().getAttributes().horizontalMargin = -0.11F;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);
        openDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){

            if(resultCode == getActivity().RESULT_OK){

                //the selected audio.
                uri = data.getData();
                //  String PATH_TO_FILE = "/sdcard/music.mp3";

                //    mp = MediaPlayer.create(getActivity(), uri);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
