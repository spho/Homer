package com.example.christoph.homer.presentation;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.christoph.homer.R;


/**
 * Created by marcel on 10/3/15.
 */
public class ApartmentFragment extends Fragment {
    private int price;
    private String image;
    private String address;
    private int travelTime;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_swipe, container, false);
        switch(Integer.valueOf(ARG_SECTION_NUMBER)) {
            case 0:
                TextView textView1 = (TextView) rootView.findViewById(R.id.pictureAboveTextView);
                TextView textView2 = (TextView) rootView.findViewById(R.id.pictureBelowTextView);
                textView1.setBackgroundColor(Integer.valueOf(ARG_SECTION_COLOR));
                textView2.setBackgroundColor(Integer.valueOf(ARG_SECTION_COLOR));
                break;
            case 1:
                textView1 = (TextView) rootView.findViewById(R.id.priceTextView);
                textView1.setBackgroundColor(Integer.valueOf(ARG_SECTION_COLOR));
                break;
            case 2:
                textView1 = (TextView) rootView.findViewById(R.id.timeTextView);
                textView1.setBackgroundColor(Integer.valueOf(ARG_SECTION_COLOR));
                break;

        }




        return rootView;
    }
    public static final String ARG_SECTION_NUMBER = "0";
    public static final String ARG_SECTION_COLOR = "0";

}
