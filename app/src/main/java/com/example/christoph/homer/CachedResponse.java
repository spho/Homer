package com.example.christoph.homer;

/**
 * Created by Christoph on 03.10.2015.
 */
public class CachedResponse {

    private String sessionid = "";
    private final int nrOfCacheStages = 2;

    //Achsen der Flats:
    //1. Achse, Current, Current.another, Current.cheaper, Current.closer
    //flats[1][2][x]= flasts[0][1][2] ?
    private Apartment[][][] flats = new Apartment[4][4][4];


    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
}
