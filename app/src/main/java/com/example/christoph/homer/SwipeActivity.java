package com.example.christoph.homer;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;

import it.gmariotti.cardslib.library.cards.actions.BaseSupplementalAction;
import it.gmariotti.cardslib.library.cards.actions.TextSupplementalAction;
import it.gmariotti.cardslib.library.cards.material.MaterialLargeImageCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardView;
import it.gmariotti.cardslib.library.view.CardViewNative;

public class SwipeActivity extends Activity implements Card.OnSwipeListener{

    private static final String TAG = "SwipeActivity: ";
    private float swipe1,swipe2;
    static final int MIN_DISTANCE = 300;

    // TODO remove
    int counter = 1;
    ArrayList<Apartment> apartementArray = new ArrayList<Apartment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_swipe);

        apartementArray.add(new Apartment(0, 1450, 40, "Schoene Wohnung in Schwamendingen", "Sehr ruhige Lage", null, "Roswiesenstrasse 120"));
        apartementArray.add(new Apartment(1, 1230, 10, "Traumhafte Wohnung", "Toller Garten!", null, "Sumpfgasse 4" ));
        apartementArray.add(new Apartment(2, 900, 45, "Wo isch de ben ond Igor Wonig", "not needed", null, "Nirgendwo 42" ));
        apartementArray.add(new Apartment(3, 4000, 5, "Schloss in der Bahnhofsstrasse", "", null, "Bahnhofsstrasse 10"));
        buildCards(apartementArray.get(0));

    }
    private Activity getActivity() {
        return this;
    }

    @Override
    public void onSwipe(Card card) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.cards_linear);

        linearLayout.removeAllViews();
        buildCards(apartementArray.get(counter));
        counter++;
        if(counter == 4) {
            counter = 0;
        }

    }

    public void buildCards(Apartment apartment) {
        //Create a Card, set the title over the image and set the thumbnail
        setContentView(R.layout.activity_swipe);

        // Set supplemental actions as text
        ArrayList<BaseSupplementalAction> actions = new ArrayList<BaseSupplementalAction>();

        // Set supplemental actions
        TextSupplementalAction t1 = new TextSupplementalAction(getActivity(), R.id.text1);
        t1.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity(), " Click on Text SHARE ", Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(t1);

        TextSupplementalAction t2 = new TextSupplementalAction(getActivity(), R.id.text2);
        t2.setOnActionClickListener(new BaseSupplementalAction.OnActionClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getActivity()," Click on Text INFO ",Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(t2);
        // TODO make this real
        int drawableWorkaround = R.drawable.flat1;
        switch(apartment.getId()) {
            case 0:
                drawableWorkaround = R.drawable.flat1;
                break;
            case 1:
                drawableWorkaround = R.drawable.flat2;
                break;
            case 2:
                drawableWorkaround = R.drawable.flat3;
                break;
            case 3:
                drawableWorkaround = R.drawable.flat4;
                break;
        }


        MaterialLargeImageCard largecardPicture =
                MaterialLargeImageCard.with(getActivity())
                 //       .setTextOverImage("Flat 1")
                        .setTitle(apartment.getTitle())
                        .setSubTitle(apartment.getAddress())
                        // TODO replace this
                        .useDrawableId(drawableWorkaround)
                  //      .setupSupplementalActions(R.layout.picture_card, actions)
                        .build();

        largecardPicture.setSwipeable(true);
        CardViewNative cardViewPicture = (CardViewNative) findViewById(R.id.card_picture);
        cardViewPicture.setCard(largecardPicture);
        //You can set a SwipeListener.
        largecardPicture.setOnSwipeListener(this);

        // Create a Card
        Card smallCardPrice = new Card(this, R.layout.small_row_card_price);
        //TextView textViewPrice = (TextView) findViewById(R.id.card_value_price);
        smallCardPrice.setTitle(String.valueOf(apartment.getPrice()) + " CHF");
        CardThumbnail thumbPrice = new CardThumbnail(this);
        thumbPrice.setDrawableResource(R.drawable.ic_keyboard_arrow_down_black_48dp);
        smallCardPrice.addCardThumbnail(thumbPrice);
        smallCardPrice.setSwipeable(true);
        CardView cardViewPrice = (CardView) findViewById(R.id.card_price);
        cardViewPrice.setCard(smallCardPrice);
        //You can set a SwipeListener.
        smallCardPrice.setOnSwipeListener(this);

        Card smallCardTime = new Card(this, R.layout.small_row_card_time);
       // TextView textViewTime = (TextView) findViewById(R.id.card_value_time);
        smallCardTime.setTitle(String.valueOf(apartment.getTraveltime() + " minutes"));
        CardThumbnail thumbTime = new CardThumbnail(this);
        thumbTime.setDrawableResource(R.drawable.ic_fast_forward_black_48dp);
        smallCardTime.addCardThumbnail(thumbTime);
        smallCardTime.setSwipeable(true);
        CardView cardViewTime = (CardView) findViewById(R.id.card_time);
        cardViewTime.setCard(smallCardTime);
        //You can set a SwipeListener.
        smallCardTime.setOnSwipeListener(this);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event)
//    {
//        switch(event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                swipe1 = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                swipe2 = event.getY();
//                float deltaY = swipe2 - swipe1;
//                if (Math.abs(deltaY) > MIN_DISTANCE)
//                {
//                    Toast.makeText(this, "Bookmarked", Toast.LENGTH_SHORT).show ();
//                }
//                else
//                {
//                    // consider as something else - a screen tap for example
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}
