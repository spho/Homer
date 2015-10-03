package com.example.christoph.homer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import com.squareup.picasso.Picasso;
/**
 * Created by marcel on 10/3/15.
 */
public class AnswerParser {
    public JSONObject convertStringToJSON(String answer) {
        try {
            return new JSONObject(answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Apartment parseJSON(JSONObject jsonObject) {
        jsonObject.length();

//        MaterialLargeImageCard.DrawableExternal drawable = new MaterialLargeImageCard.DrawableExternal() {
//            @Override
//            public void setupInnerViewElements(ViewGroup parent, View viewImage) {
//
//                Picasso.with(getActivity())
//                        .load("https://lh5.googleusercontent.com/-squZd7FxR8Q/UyN5UrsfkqI/AAAAAAAAbAo/VoDHSYAhC_E/s96/new%2520profile%2520%25282%2529.jpg")
//                        .error(R.drawable.ic_error_loadingsmall)
//                        .into((ImageView) viewImage);
//            }
//        }
        int id = 0, price = 0, traveltime = 0;
        String img = null, address = null, title = null;
        float rooms = 0.0f;

        try {
            id = jsonObject.getInt("id");
            price = jsonObject.getInt("price");
            traveltime = jsonObject.getInt("traveltime");
            img = jsonObject.getString("img");
            address = jsonObject.getString("address");
            title = jsonObject.getString("title");
            rooms = (float)jsonObject.getDouble("rooms");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Apartment result = new Apartment(id, price, traveltime, title, "", img, address, rooms, 0);
        return result;
    }
}
