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
    public Apartment[][] parse(String answer) {
        JSONObject jsonObject = convertStringToJSON(answer);
        return parseJSON(jsonObject);
    }
    private JSONObject convertStringToJSON(String answer) {
        try {
            return new JSONObject(answer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Apartment[][] parseJSON(JSONObject jsonObject) {
        jsonObject.length();
        Apartment[][] apartments = new Apartment[4][4];
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
        try {

            for (int i = 0; i < 4; i++) {
                JSONObject root = null;
                switch (i) {
                    case 0:
                        root = jsonObject.getJSONObject("current");
                        break;
                    case 1:
                        root = jsonObject.getJSONObject("another");
                        break;
                    case 2:
                        root = jsonObject.getJSONObject("cheaper");
                        break;
                    case 3:
                        root = jsonObject.getJSONObject("closer");
                        break;
                }
                if(root == null) {
                    throw new JSONException("Fail");
                }
                apartments[i][0] = parseApartment(root);
                if(i!=0) {
                    JSONObject another = root.getJSONObject("another");
                    apartments[i][1] = parseApartment(another);
                    JSONObject cheaper = root.getJSONObject("cheaper");
                    apartments[i][2] = parseApartment(cheaper);
                    JSONObject closer = root.getJSONObject("closer");
                    apartments[i][3] = parseApartment(closer);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return apartments;
    }
    private Apartment parseApartment(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        int price = jsonObject.getInt("price");
        int traveltime = jsonObject.getInt("traveltime");
        String img = jsonObject.getString("img");
        String address = jsonObject.getString("address");
        String title = jsonObject.getString("title");
        Float rooms = (float) jsonObject.getDouble("rooms");
        return new Apartment(id, price, traveltime, title, "", img, address, rooms, 0);
    }
}
