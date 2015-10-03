package com.example.christoph.homer;

import android.app.Activity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
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

public class SwipeActivity extends Activity implements Card.OnSwipeListener {

    private static final String TAG = "SwipeActivity: ";
    private float x1, x2, y;
    static final int MIN_DISTANCE = 150;
    private int screenUnit;
    private int actionBarHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_swipe);
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int displayWidth = size.x;
        int displayHeight = size.y;
        screenUnit = (displayHeight - actionBarHeight) / 6;
        Log.i(TAG, "Screenunit: " + String.valueOf(screenUnit));
        Log.i(TAG, "ActionBarHeight: " + String.valueOf(screenUnit));

        buildCards();

    }
    private Activity getActivity() {
        return this;
    }

    @Override
    public void onSwipe(Card card) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.cards_linear);
        linearLayout.removeAllViews();
        buildCards();

    }

    public void buildCards() {
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
                Toast.makeText(getActivity()," Click on Text LEARN ",Toast.LENGTH_SHORT).show();
            }
        });
        actions.add(t2);

        MaterialLargeImageCard largecardPicture =
                MaterialLargeImageCard.with(getActivity())
                        .setTextOverImage("Flat 1")
                        .setTitle("Flat 1")
                        .setSubTitle("A wonderful place")
                        .useDrawableId(R.drawable.flat1)
                  //      .setupSupplementalActions(R.layout.picture_card, actions)
                        .build();

        largecardPicture.setSwipeable(true);
        CardViewNative cardViewPicture = (CardViewNative) findViewById(R.id.card_picture);
        cardViewPicture.setCard(largecardPicture);
        //You can set a SwipeListener.
        largecardPicture.setOnSwipeListener(this);

        // Create a Card
        Card smallCardPrice = new Card(this, R.layout.row_card);
        smallCardPrice.setTitle("Price");
        CardThumbnail thumbPrice = new CardThumbnail(this);
        thumbPrice.setDrawableResource(android.R.drawable.ic_delete);
        smallCardPrice.addCardThumbnail(thumbPrice);
        smallCardPrice.setSwipeable(true);
        CardView cardViewPrice = (CardView) findViewById(R.id.card_price);
        cardViewPrice.setCard(smallCardPrice);
        //You can set a SwipeListener.
        smallCardPrice.setOnSwipeListener(this);

        Card smallCardTime = new Card(this, R.layout.row_card);
        smallCardTime.setTitle("Price");
        CardThumbnail thumbTime = new CardThumbnail(this);
        thumbTime.setDrawableResource(android.R.drawable.ic_btn_speak_now);
        smallCardTime.addCardThumbnail(thumbTime);
        smallCardTime.setSwipeable(true);
        CardView cardViewTime = (CardView) findViewById(R.id.card_time);
        cardViewTime.setCard(smallCardTime);
        //You can set a SwipeListener.
        smallCardTime.setOnSwipeListener(this);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = event.getX();
//                y = event.getY();
//                Log.i(TAG, String.valueOf(y));
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = event.getX();
//                float deltaX = x2 - x1;
//
//
//                if (Math.abs(deltaX) > MIN_DISTANCE) {
//                    // Left to Right or Right to left swipe action
//                    if (y >= actionBarHeight && y < actionBarHeight + 4 * screenUnit) {
//                        // picture frame
//                        //Toast.makeText(this, "Swipe high", Toast.LENGTH_SHORT).show();
//                        TextView textView1 = (TextView) findViewById(R.id.pictureAboveTextView);
//                        TextView textView2 = (TextView) findViewById(R.id.pictureBelowTextView);
//                        Random rnd = new Random();
//                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//                        textView1.setBackgroundColor(color);
//                        textView2.setBackgroundColor(color);
//                    } else if (y >= actionBarHeight + 4 * screenUnit && y < actionBarHeight + 5 * screenUnit) {
//                        // price frame
//                        //Toast.makeText(this, "Swipe middle", Toast.LENGTH_SHORT).show ();
//                        TextView textView = (TextView) findViewById(R.id.priceTextView);
//                        Random rnd = new Random();
//                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//                        textView.setBackgroundColor(color);
//                    } else if (y >= actionBarHeight + 5 * screenUnit) {
//                        // distance frame
//                        //Toast.makeText(this, "Swipe low", Toast.LENGTH_SHORT).show ();
//                        TextView textView = (TextView) findViewById(R.id.timeTextView);
//                        Random rnd = new Random();
//                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//                        textView.setBackgroundColor(color);
//                    }
//                } else {
//                    // consider as something else - a screen tap for example
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}
