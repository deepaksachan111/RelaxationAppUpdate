package com.server18.relaxationapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.server18.relaxationapp.R;
import com.server18.relaxationapp.fragment.AudioFragment;
import com.server18.relaxationapp.fragment.BooksFragment;

public class EventBookAudioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_book_audio);

          boolean b = getIntent().getBooleanExtra("B",false);
          boolean c   = getIntent().getBooleanExtra("C",false);
        if(c){

            getSupportFragmentManager().beginTransaction().add(R.id.activity_event_book_audio, new AudioFragment()).commit();

        }else

        if(b){

            getSupportFragmentManager().beginTransaction().add(R.id.activity_event_book_audio, new BooksFragment()).commit();

        }else {


            getSupportFragmentManager().beginTransaction().add(R.id.activity_event_book_audio, new EventbookaudioFragment()).commit();

        }


        findViewById(R.id.books_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_event_book_audio, new BooksFragment()).commit();
            }
        });

        findViewById(R.id.event_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_event_book_audio, new EventbookaudioFragment()).commit();
            }
        });

        findViewById(R.id.audio_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_event_book_audio, new AudioFragment()).commit();
            }
        });


    }




    public class EventbookaudioFragment extends Fragment {


        public EventbookaudioFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_event, container, false);








            return v;
        }


    }


}
