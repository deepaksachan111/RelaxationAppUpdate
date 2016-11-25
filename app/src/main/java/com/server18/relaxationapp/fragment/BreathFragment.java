package com.server18.relaxationapp.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.server18.relaxationapp.R;
import com.server18.relaxationapp.UrlUtilities;
import com.server18.relaxationapp.activity.BreathItemActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */

public class BreathFragment extends Fragment {
    private  ProgressDialog pd;

    private   ArrayAdapter arrayAdapter;
    private TextView  breath_frag_starttime;
    LinearLayout linear_technique_breath_vis;

    private  ListView listView;
    private  ArrayList<String> arrayList_breathdata = new ArrayList<>();

    private long startTime = 0L;
    long timeInMilliseconds = 0L;

    long updatedTime = 0L;
    int secs;
    int mins;

    Handler handler = new Handler();
    public BreathFragment() {
        // Required empty public constructor
    }

    public static BreathFragment newInstance(int pos,String name) {
        BreathFragment f = new BreathFragment();
        Bundle args = new Bundle();
        args.putInt("pos", pos);
        args.putString("name",name);
        f.setArguments(args);
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_breath, container, false);
        showProgressDialog();
        listView =(ListView)v.findViewById(R.id.listview_breath_fragment);
        arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList_breathdata);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), BreathItemActivity.class);
                intent.putExtra("item",item);
             //   startActivity(intent);

            }
        });

         /*

       int pos =   getArguments().getInt("pos", 0);
        String name = getArguments().getString("name");

        TextView textView =(TextView)v.findViewById(R.id.tv_breathtetxv);
        TextView textViewpos =(TextView)v.findViewById(R.id.tv_breathtetxv_position);


        textView.setText(name);
        textViewpos.setText(pos + "");

        breath_frag_starttime=(TextView)v.findViewById(R.id.breath_frag_starttime);
        linear_technique_breath_vis=(LinearLayout)v.findViewById(R.id.linear_visibility_first);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime();

                // reverseTimer(40, breath_frag_starttime);

                timerTime();
            }
        });

         */


        registerUserPost(UrlUtilities.BREATHE_URL);
        return v;
    }



    private void registerUserPost(String REGISTER_URL) {
        REGISTER_URL = UrlUtilities.BREATHE_URL;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    int count = 0;
                    @Override
                    public void onResponse(String response) {
                 //      Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("info");
                            for(int i = 0; i<jsonArray.length();i++){

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String    category_name = jsonObject1.getString("sub_category_name");

                                count++;
                               arrayList_breathdata.add(category_name);
                                if(count == 7){
                                    break;
                                }

                            }

                           listView.setAdapter(arrayAdapter);

                          //  Toast.makeText(getApplicationContext(),"No of records : "+count+"",Toast.LENGTH_LONG).show();
                           // setListAdapter(adapter);
                          //  ListViewHelper.getListViewSize(listView);
                            //  String resp = jsonObject.getString("RetSiteResult");

                            pd.dismiss();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("cm_name", "breathe");
                params.put("sub_category_name", "test");
                return params;
            }

        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
     //   stringRequest.setShouldCache(false);
        requestQueue.add(stringRequest);
       // AppController.getInstance().addToRequestQueue(stringRequest);
    }



    private void showProgressDialog(){
        pd = new ProgressDialog(getActivity(),R.style.StyledDialog);
        // Set progress dialog style spinner
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading.........");
        pd.setIndeterminate(false);
        // Finally, show the progress dialog
        pd.show();

        // Set the progress status zero on each button click

    }



    private void setTime(){

        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(updateTimerThread, 0);
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            //  updatedTime = timeSwapBuff + timeInMilliseconds;
            updatedTime = timeInMilliseconds;
            breath_frag_starttime.setText("");
            secs = (int) (updatedTime / 1000);
            mins = secs / 60;
            secs = secs % 60;


            int milliseconds = (int) (updatedTime % 1000);
            breath_frag_starttime.setText("" + mins + ":"  + String.format("%02d", secs) );
            handler.postDelayed(this, 0);
        }

    };


    public void reverseTimer(final int Seconds,final TextView tv){

        new CountDownTimer(Seconds* 1000+1000,1000) {

            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                tv.setText("TIME : " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));


            }

            public void onFinish() {


                tv.setText("Completed");


            }
        }.start();
    }


    private  void timerTime() {

           Timer myTimer = new Timer();
        TimerTask myTimerTask = new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                       handler.removeCallbacks(updateTimerThread);
                       breath_frag_starttime.setText("finish");

                    }
                });

            }
        };

        myTimer.scheduleAtFixedRate( myTimerTask, 20000, 40000);

    }
}
