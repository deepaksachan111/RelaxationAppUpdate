package com.server18.relaxationapp.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.server18.relaxationapp.R;
import com.server18.relaxationapp.UrlUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SoundFragment extends Fragment {

private  ProgressDialog pd;
    private  ArrayAdapter arrayAdapter;
    private ListView listView;
    private    ArrayList<String> arrayList_breathdatas = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sound, container, false);
        listView =(ListView)v.findViewById(R.id.listview_sound_fragment);
        arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,arrayList_breathdatas);
        showProgressDialog();
        registerUserPost(UrlUtilities.SOUNDS_URL);
        return v;
    }


    private void registerUserPost(String REGISTER_URL) {
        REGISTER_URL = UrlUtilities.SOUNDS_URL;


        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    int count = 0;
                    @Override
                    public void onResponse(String response) {
                   //    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            JSONArray jsonArray = jsonObject.getJSONArray("info");
                            for(int i = 0; i<jsonArray.length();i++){

                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                String    category_name = jsonObject1.getString("sub_category_name");

                                count++;
                                arrayList_breathdatas.add(category_name);


                            }

                            listView.setAdapter(arrayAdapter);

                            //  Toast.makeText(getApplicationContext(),"No of records : "+count+"",Toast.LENGTH_LONG).show();
                            // setListAdapter(adapter);
                            //  ListViewHelper.getListViewSize(listView);
                            //  String resp = jsonObject.getString("RetSiteResult");





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        pd.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("cm_name", "sounds");
                params.put("sub_category_name", "test");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        stringRequest2.setShouldCache(false);
        requestQueue.add(stringRequest2);
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


}
