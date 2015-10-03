package com.example.christoph.homer;

import java.io.InputStream;

/**
 * Created by Christoph on 03.10.2015.
 */
public class InputValues {

    private Location location = new Location();
    private int moneyLevel = -1;
    private int lowerRoomBoundary = -1;
    private int upperRoomBoundary = -1;

    public InputValues(){

    }

    public InputValues(Location loc, int ml, int lrb, int urb) {
        location = loc;
        moneyLevel = ml;
        lowerRoomBoundary = lrb;
        upperRoomBoundary = urb;
    }

    public Location getLocation() {
        return location;
    }

    public int getMoneyLevel() {
        return moneyLevel;
    }

    public int getLowerRoomBoundary() {
        return lowerRoomBoundary;
    }

    public int getUpperRoomBoundary() {
        return upperRoomBoundary;
    }

    public String serialise() {
        String str;
        str = location.serialise() + ";" + moneyLevel + ";" + lowerRoomBoundary + ";" + upperRoomBoundary;
        return str;
    }

    public void desirialise(String str){
        String[] parts = str.split("\\;");
        location.desirialise(str);
        moneyLevel=Integer.parseInt(parts[6]);
        lowerRoomBoundary=Integer.parseInt(parts[7]);
        upperRoomBoundary=Integer.parseInt(parts[8]);

    }


}
