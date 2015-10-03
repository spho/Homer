package com.example.christoph.homer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {


    RangeSeekBar<Integer> seekBar;
    Location location = null;

    private Button[] buttons = new Button[3];
    private TextView[] textView = new TextView[2];
    private EditText editText;
    private Button goButton;

    //Low =1, mid =2, high = 3
    private int choosenMoneyRange = 2;
    private int lowBoundary = 1;
    private int highBoundary = 8;


    private boolean debug = true;

    private boolean selectiondone = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        initUI();

        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {

            String address = (String) b.get("address");
            String city = (String) b.get("city");
            String state = (String) b.get("state");
            String country = (String) b.get("country");
            String postalCode = (String) b.get("postal");
            String knownName = (String) b.get("knownname");



            location = new Location(address, city, state, country, postalCode, knownName);
            if(address!=city) {
                editText.setText(address + ", " + city);
            }
            else{
                editText.setText(address);
            }


        }



        switch (choosenMoneyRange) {
            case 1:
                buttons[0].setPressed(true);
                buttons[1].setPressed(false);
                buttons[2].setPressed(false);
                break;
            case 2:
                buttons[0].setPressed(false);
                buttons[1].setPressed(true);
                buttons[2].setPressed(false);
                break;
            case 3:
                buttons[0].setPressed(false);
                buttons[1].setPressed(false);
                buttons[2].setPressed(true);
                break;
            default:
                break;


        }


    }


    private void initUI() {
        buttons[0] = (Button) findViewById(R.id.button);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);

        textView[0] = (TextView) findViewById(R.id.textView);
        textView[1] = (TextView) findViewById(R.id.textView2);

        goButton = (Button) findViewById(R.id.button4);

        buttons[0].setAlpha(0.2f);
        buttons[1].setAlpha(1f);
        buttons[2].setAlpha(0.2f);


        // create RangeSeekBar as Integer range between 2 and 16
        seekBar = new RangeSeekBar<Integer>(2, 16, this);
        textView[0].setText("1");
        textView[1].setText("8");


        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                lowBoundary = minValue;
                highBoundary = maxValue;

                textView[0].setText("" + (float) (minValue) / 2);
                textView[1].setText("" + (float) (maxValue) / 2);
                Log.i("TAG1", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });
        editText = (EditText) findViewById(R.id.editText);


        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v == (View) editText) {
                    changeToMap();
                }
                if (v == (View) buttons[0]) {
                    choosenMoneyRange = 1;
                    buttons[0].setAlpha(1f);
                    buttons[1].setAlpha(0.2f);
                    buttons[2].setAlpha(0.2f);
                }
                if (v == (View) buttons[1]) {
                    choosenMoneyRange = 2;
                    buttons[0].setAlpha(0.2f);
                    buttons[1].setAlpha(1f);
                    buttons[2].setAlpha(0.2f);
                }
                if (v == (View) buttons[2]) {
                    choosenMoneyRange = 3;
                    buttons[0].setAlpha(0.2f);
                    buttons[1].setAlpha(0.2f);
                    buttons[2].setAlpha(1f);
                }
                if (v == (View) goButton) {
                    if (selectiondone||debug) {
                        exitFormAndGoToSwiping();
                    }
                }
                return true; // return is important...
            }
        };

        editText.setOnTouchListener(onTouchListener);
        goButton.setOnTouchListener(onTouchListener);
        buttons[0].setOnTouchListener(onTouchListener);
        buttons[1].setOnTouchListener(onTouchListener);
        buttons[2].setOnTouchListener(onTouchListener);

        // add RangeSeekBar to pre-defined layout
        ViewGroup layout = (ViewGroup) findViewById(R.id.layout_for_slider);
        layout.addView(seekBar);
    }

    private void exitFormAndGoToSwiping() {



        /*
        String str = input.serialise();
        InputValues in2 = new InputValues();
        in2.desirialise(str);
        */

        Intent intent = new Intent(this, SwipeActivity.class);
        if(!debug){
            InputValues input = new InputValues(location, choosenMoneyRange, lowBoundary, highBoundary);
            intent.putExtra("InputValues", input.serialise());
        }
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void changeToMap() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }


}
