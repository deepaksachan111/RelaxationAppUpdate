package com.server18.relaxationapp.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.server18.relaxationapp.R;


public class BooksFragment extends android.support.v4.app.Fragment {


    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_books, container, false);

        return v;

    }


}
