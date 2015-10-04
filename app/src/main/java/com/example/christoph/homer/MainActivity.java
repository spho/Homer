package com.example.christoph.homer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import java.text.Normalizer;


public class MainActivity extends Activity {


    RangeSeekBar<Integer> seekBar;
    Location location = null;

    private Button[] buttons = new Button[3];
    private TextView[] textView = new TextView[2];
    private EditText editText;
    private Button goButton;
    private final String URL = "http://homer-data.azurewebsites.net/";


    //Low =1, mid =2, high = 3
    private int choosenMoneyRange = 2;
    private int lowBoundary = 1;
    private int highBoundary = 8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ActionBar actionBar = getActionBar();
        actionBar.setCustomView(R.layout.customactionbar);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);


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

            goButton.setAlpha(1f);
            goButton.setClickable(true);

            location = new Location(address, city, state, country, postalCode, knownName);
            if (address != city) {
                editText.setText(address + ", " + city);
            } else {
                editText.setText(address);
            }


        }


        switch (CachedMainSettings.getInstance().getSavedDollarSelector()) {
            case 1:
                buttons[0].setPressed(true);
                buttons[1].setPressed(false);
                buttons[2].setPressed(false);
                buttons[0].setAlpha(1f);
                buttons[1].setAlpha(0.2f);
                buttons[2].setAlpha(0.2f);
                choosenMoneyRange = 1;
                break;
            case 2:
                buttons[0].setPressed(false);
                buttons[1].setPressed(true);
                buttons[2].setPressed(false);
                buttons[0].setAlpha(0.2f);
                buttons[1].setAlpha(1f);
                buttons[2].setAlpha(0.2f);
                choosenMoneyRange = 2;
                break;
            case 3:
                buttons[0].setPressed(false);
                buttons[1].setPressed(false);
                buttons[2].setPressed(true);
                buttons[0].setAlpha(0.2f);
                buttons[1].setAlpha(0.2f);
                buttons[2].setAlpha(1f);
                choosenMoneyRange = 3;
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
        goButton.setAlpha(0.2f);
        goButton.setClickable(false);

        buttons[0].setAlpha(0.2f);
        buttons[1].setAlpha(1f);
        buttons[2].setAlpha(0.2f);


        // create RangeSeekBar as Integer range between 2 and 16
        seekBar = new RangeSeekBar<Integer>(2, 16, this);
        textView[0].setText("1 rooms");
        textView[1].setText("8 rooms");


        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                lowBoundary = minValue;
                highBoundary = maxValue;

                textView[0].setText("" + (float) (minValue) / 2 + " rooms");
                textView[1].setText("" + (float) (maxValue) / 2 + " rooms");
                Log.i("TAG1", "User selected new range values: MIN=" + minValue + ", MAX=" + maxValue);
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        if (editText.length() != 0) {
            editText.setBackgroundColor(Color.TRANSPARENT);
            editText.setFocusableInTouchMode(true);
        } else {
            editText.setFocusable(false);
        }

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.ACTION_UP == MotionEvent.ACTION_UP) {
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
                    CachedMainSettings.getInstance().setSavedDollarSelector(choosenMoneyRange);
                    if (v == (View) goButton) {

                        if (location != null) {
                            if (location.getPostalCode() != null && location.getCity() != null && location.getAddress() != null) {
                                sendInitToServer();
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Location selected", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid Location selected", Toast.LENGTH_SHORT).show();
                        }

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

    public void exitFormAndGoToSwiping() {

        /*
        String str = input.serialise();
        InputValues in2 = new InputValues();
        in2.desirialise(str);
        */

        Intent intent = new Intent(this, SwipeActivity.class);

        InputValues input = new InputValues(location, choosenMoneyRange, lowBoundary, highBoundary);
        intent.putExtra("InputValues", input.serialise());
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

    private void sendInitToServer() {
        InputValues inputValues = new InputValues(location, choosenMoneyRange, lowBoundary, highBoundary);
        String ad = inputValues.getLocation().getAddress() + "+" + inputValues.getLocation().getCity();
        ad = ad.replaceAll(" ", "+");
        String ml;
        switch (inputValues.getMoneyLevel()) {
            case 1:
                ml = "low";
                break;
            case 2:
                ml = "med";
                break;
            case 3:
                ml = "high";
                break;
            default:
                ml = "";
                break;
        }
        goButton.setClickable(false);
        goButton.setEnabled(false);
        goButton.setBackgroundColor(Color.WHITE);
        goButton.setShadowLayer(0, 0, 0, Color.WHITE);
        goButton.setText("Loading");
        String theEvilString = ad;
        theEvilString = Normalizer.normalize(theEvilString, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        String st = URL + "init?address=" + theEvilString + "&roomslower=" + (float) ((float) (inputValues.getLowerRoomBoundary()) / 2) + "&roomsupper=" + (float) ((float) (inputValues.getUpperRoomBoundary()) / 2) + "&pricelevel=" + ml + "&zip=" + inputValues.getLocation().getPostalCode();


        Log.i("TAG", "Send init request: " + st);
        new RequestTask(true, this).execute(st);
    }


}
