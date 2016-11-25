package com.server18.relaxationapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.server18.relaxationapp.activity.BreathScheduleActivity;
import com.server18.relaxationapp.activity.EventBookAudioActivity;
import com.server18.relaxationapp.fragment.MeditationAlamFragment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener,View.OnClickListener{
    private SliderLayout mDemoSlider;
    private LinearLayout linearlayout_meditation, linear_courage,linear_breath;
    private ImageView imageView_home_bottom,iv_courage,iv_home_menudrawer,iv_meditation,iv_breath;
    private TextView  tv_courage,tv_meditaion,tv_breath;
    private Handler handler = new Handler();
    Animation animationtv;
    int counter =0;
    private  int count = 0;
    private Dialog dialogPopup;
    int a;
    private  boolean flag ;
    ImageView iv_show_all;
    Toolbar toolbar;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
          Intent i = getActivity().getIntent();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
      // a  = i.getIntExtra("int",0);
        dialogPopup  = new Dialog(getActivity(),R.style.DialogSlideAnim);
        toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
        ImageView iv_shopping_cart =(ImageView)toolbar.findViewById(R.id.iv_shoping_cart);
        iv_show_all =(ImageView)toolbar.findViewById(R.id.iv_showall);

          iv_show_all.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(getActivity().getApplicationContext(), EventBookAudioActivity.class));
              }
          });

        iv_shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(), "Shopping item added on working", Toast.LENGTH_LONG).show();
            }
        });

          //  a  = i.getIntExtra("int",0);

        String value = sharedPreferences.getString("first","");
        String vv = value;

        animationtv = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
        showslide(v);
        findIDS(v);


             if (value == "") {
                 popupStart();


                 editor.putString("first", "1");
                 editor.commit();
             }




   /*     Thread thread = new Thread(){
            final Dialog dialog = new Dialog(getActivity());
            public void run(){
                Looper.prepare();
                try {
                    Thread.sleep(13000);

                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(R.layout.dialoge);
                    //  dialog.setTitle("Title...");
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    // set the custom dialog components - text, image and button

               dialog.show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    dialog.dismiss();
                }

                Looper.loop();
            }


        };thread.start();*/


        return v;
    }


    private void popupStart(){
    /*    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("How to close alertdialog programmatically");
        builder.setMessage("5 second dialog will close automatically");
        builder.setCancelable(true);

        final AlertDialog closedialog= builder.create();

        closedialog.show();*/


        final Handler handler = new Handler();

        dialogPopup.getWindow().setGravity(Gravity.CENTER);
        dialogPopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPopup.setCancelable(false);
        dialogPopup.setContentView(R.layout.custom_popup_dialog);
     LinearLayout   li_custum_dialoge_event = (LinearLayout)dialogPopup.findViewById(R.id.li_custum_dialoge_event);

        li_custum_dialoge_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_show_all.setVisibility(View.VISIBLE);
                startActivity(new Intent(getActivity(), EventBookAudioActivity.class));
            }
        });
        LinearLayout   li_custum_dialoge_books = (LinearLayout)dialogPopup.findViewById(R.id.li_custum_dialoge_books);

        li_custum_dialoge_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean b = true;
                iv_show_all.setVisibility(View.VISIBLE);
              Intent intent = new Intent(getActivity(), EventBookAudioActivity.class);
                intent.putExtra("B", b);

                startActivity(intent);
            }
        });
        LinearLayout    li_custum_dialoge_audio = (LinearLayout)dialogPopup.findViewById(R.id. li_custum_dialoge_audio);
        li_custum_dialoge_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean c =true;
                iv_show_all.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getActivity(), EventBookAudioActivity.class);
                intent.putExtra("C",c);
                startActivity(intent);
            }
        });

        TextView text = (TextView) dialogPopup.findViewById(R.id.text_dialog);
           text.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   iv_show_all.setVisibility(View.VISIBLE);
                   dialogPopup.dismiss();
               }
           });
        final Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iv_show_all.setVisibility(View.VISIBLE);
                        }
                    });
                dialogPopup.dismiss();

                timer2.cancel(); //this will cancel the timer of the system

            }
        }, 5000); //

        dialogPopup.show();
    }



    private void showslide(View v ){

        mDemoSlider = (SliderLayout)v.findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new LinkedHashMap<>();
        file_maps.put("one", R.drawable.image5);
        file_maps.put("two",R.drawable.image6);
        file_maps.put("three", R.drawable.image7);
        file_maps.put("four", R.drawable.nature1);
        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);



        //  Menu menuNav = getMenu();



    }

    private void findIDS(View view) {
        tv_breath=(TextView)view.findViewById(R.id.tv_breath_home_frage);
        iv_breath =(ImageView)view.findViewById(R.id.iv_breath_home_frage);
        iv_courage =(ImageView)view.findViewById(R.id.iv_courage);


        linear_breath = (LinearLayout)view. findViewById(R.id.linearlayout_breath);
        linearlayout_meditation = (LinearLayout)view. findViewById(R.id.linearlayout_meditation);
        iv_meditation  =(ImageView)view.findViewById(R.id.iv_meditation);
        tv_meditaion  =(TextView)view.findViewById(R.id.tv_meditation);


        tv_meditaion.setOnClickListener(this);
        iv_meditation.setOnClickListener(this);


        iv_courage.setOnClickListener(this);
        linearlayout_meditation.setOnClickListener(this);
        linear_breath.setOnClickListener(this);
        iv_breath.setOnClickListener(this);
        tv_breath.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        if(v == imageView_home_bottom){
            Toast.makeText(getActivity(), "home", Toast.LENGTH_LONG).show();
        }else
        if( v ==iv_courage){
            Toast.makeText(getActivity(), "Courage", Toast.LENGTH_LONG).show();

            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_animation);
            Animation animationtv = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
            iv_courage.startAnimation(animation);


            custumDialog();

        }

        else if(v ==linearlayout_meditation||v== tv_meditaion ||v== iv_meditation){
            //String  uri="http://videos1.djmazadownload.com/music/Singles/Dillagi%20-%20Rahat%20Fateh%20Ali%20Khan%20-190Kbps%20[DJMaza.Link].mp3";
            //  playSoundForXSeconds(uri,50);
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_animation);
            Animation animationtv = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
            tv_meditaion.startAnimation(animation);
            iv_meditation.setAnimation(animationtv);
            getFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag,new MeditationAlamFragment()).commit();
        }

        else if(v ==linear_breath||v== tv_breath ||v== iv_breath){
            //String  uri="http://videos1.djmazadownload.com/music/Singles/Dillagi%20-%20Rahat%20Fateh%20Ali%20Khan%20-190Kbps%20[DJMaza.Link].mp3";
            //  playSoundForXSeconds(uri,50);
            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_in_animation);
            Animation animationtv = AnimationUtils.loadAnimation(getActivity(), R.anim.bounce);
            tv_breath.startAnimation(animation);
            iv_breath.setAnimation(animationtv);
            startActivity(new Intent(getActivity(), BreathScheduleActivity.class));
            //getFragmentManager().beginTransaction().replace(R.id.frameLayout_home_frag,new MeditationAlamFragment()).commit();
        }

    }


    /*private Runnable method(final TextView text){
        Runnable handlerText = new Runnable() {
            final String quotes[] = new String[]{"Meditation is the discovery that the point of life is always arrived at in the immediate moment. – Alan Watts",
                    "Meditation practice isn’t about trying to throw ourselves away and become something better, it’s about befriending who we are. – Ani Pema Chodron",
                    "Meditation is not a means to an end. It is both the means and the end. – Jiddu Krishnamurti",
                    "The you that goes in one side of the meditation experience is not the same you that comes out the other side. – Bhante Henepola Gunaratana",
                    "If every 8-year old in the world is taught meditation, we will eliminate violence from the world within one generation. – Dalai Lama",
                    "Be conscious of yourself as consciousness alone, watch all the thoughts come and go. Come to the conclusion, by direct experience, that you are really consciousness itself, not its ephemeral contents. – Annamalai Swami",
                    "Meditation is not a way of making your mind quiet. It’s a way of entering into the quiet that’s already there – buried under the 50,000 thoughts the average person thinks every day. – Deepak Chopra"};

            @Override
            public void run() {

                int length = quotes[counter].length();
                Spannable wordtoSpan = new SpannableString(quotes[counter]);
                final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);



                if(counter == 0){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 97, 109, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 97, 109, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    counter ++;
                }else if(counter ==1){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 126, 145, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 126, 145, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    counter ++;
                }else if(counter ==2){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 70, 91, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 71, 91, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);

                  //  text.setAnimation(animationtv);
                    counter ++;
                }else if(counter ==3){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 111, 138, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 111, 138, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                  //  text.setAnimation(animationtv);
                    counter ++;
                }else if(counter ==4){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 120, 132, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 120, 132, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    counter ++;
                }else if(counter ==5){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 202, 218, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 202, 218, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    counter ++;
                }else if(counter ==6){

                    counter=0;
                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 182, 197, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 182, 197, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);


                }

                handler.postDelayed(this,4000);
            }



        };

        return handlerText;
    }*/



    private void custumDialog(){

        final String quotes[] = new String[]{"Meditation is the discovery that the point of life is always arrived at in the immediate moment. – Alan Watts",
                "Meditation practice isn’t about trying to throw ourselves away and become something better, it’s about befriending who we are. – Ani Pema Chodron",
                "Meditation is not a means to an end. It is both the means and the end. – Jiddu Krishnamurti",
                "The you that goes in one side of the meditation experience is not the same you that comes out the other side. – Bhante Henepola Gunaratana",
                "If every 8-year old in the world is taught meditation, we will eliminate violence from the world within one generation. – Dalai Lama",
                "Be conscious of yourself as consciousness alone, watch all the thoughts come and go. Come to the conclusion, by direct experience, that you are really consciousness itself, not its ephemeral contents. – Annamalai Swami",
                "Meditation is not a way of making your mind quiet. It’s a way of entering into the quiet that’s already there – buried under the 50,000 thoughts the average person thinks every day. – Deepak Chopra"};

        final Dialog dialog = new Dialog(getActivity());
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialoge);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Set dialog title
        // dialog.setTitle("Custom Dialog");

        // set values for custom dialog components - text, image and button
        final TextView text = (TextView) dialog.findViewById(R.id.textDialog);
        ImageView iv_nextText=(ImageView)dialog.findViewById(R.id.imageDialog);

        // text.setText(quotes[count]);
        Spannable wordtoSpan = new SpannableString(quotes[count]);
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 97, 109, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(bss, 97, 109, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setText(wordtoSpan);
        text.setAnimation(animationtv);

        iv_nextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // text.setText(quotes[count]);
                // count++;

                int length = quotes[count].length();
                Spannable wordtoSpan = new SpannableString(quotes[count]);
                final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);



                if(count == 0){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 97, 109, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 97, 109, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    count++;
                }else if(count ==1){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 126, 145, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 126, 145, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    count++;
                }else if(count ==2){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 70, 91, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 71, 91, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);

                    //  text.setAnimation(animationtv);
                    count ++;
                }else if(count ==3){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 111, 138, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 111, 138, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    //  text.setAnimation(animationtv);
                    count ++;
                }else if(count ==4){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 120, 132, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 120, 132, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    count ++;
                }else if(count ==5){

                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 202, 218, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 202, 218, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);
                    count ++;
                }else if(count ==6){

                    count=0;
                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), 182, 197, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(bss, 182, 197, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    text.setText(wordtoSpan);
                    text.setAnimation(animationtv);


                }

/*
                   if(count == quotes.length){
                          count =0;
                   }
                   */

            }
        });
        // handler.postDelayed(method(text),1000);

        ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
        //image.setImageResource(R.drawable.close_2);

        // dialog.show();

        ImageView declineButton = (ImageView) dialog.findViewById(R.id.declineButton);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
                //  handler.removeCallbacks(method(text));

            }
        });

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

        dialog.getWindow().setLayout(width, height / 2);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setGravity(Gravity.CENTER | Gravity.CENTER);
        //  dialog.getWindow().getAttributes().verticalMargin = 0.50F;
        // dialog.getWindow().getAttributes().horizontalMargin = 0.09F;
        //wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        //  window.setAttributes(wlp);
        dialog.show();


    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {

    }


 /*   private void playSoundForXSeconds( final String soundUri, final int seconds) {
        if(soundUri!=null) {

            Thread thread = new Thread(){

                public void run(){
                    Looper.prepare();
                    final MediaPlayer mp = new MediaPlayer();
                    // final   MediaPlayer  mp = MediaPlayer.create(getActivity(),soundUri);

                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mp.setDataSource(soundUri);
                        mp.prepare();
                        mp.start();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }

                    Handler mHandler = new Handler();
                    mHandler.postDelayed(new Runnable() {
                        public void run() {
                            try {
                                mp.stop();
                            }catch(Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, seconds * 1000);
                    Looper.loop();

                }
            };thread.start();
            //MediaPlayer ma = MediaPlayer.create(getActivity(), Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/Music/intro.mp3"));


        }
    }*/

    @Override
    public void onPause() {
        super.onPause();

        dialogPopup.dismiss();

    }
}


