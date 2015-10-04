package com.example.christoph.homer;

/**
 * Created by Christoph on 03.10.2015.
 */
public class CachedResponse {
    private static CachedResponse instance = null;

    @Deprecated
    protected CachedResponse() {
        // Exists only to defeat instantiation.
    }
    public static CachedResponse getInstance() {
        if(instance == null) {
            // allowed in this particular case
            instance = new CachedResponse();
        }
        return instance;
    }

    private String sessionid = "";
    private final int nrOfCacheStages = 2;

    //Achsen der Flats:
    //1. Achse, Current, Current.another, Current.cheaper, Current.closer
    //flats[1][2][x]= flasts[0][1][2] ?
    private Apartment[] apartments = new Apartment[4];


    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public Apartment getApartment(int index) {
        return apartments[index];
    }

    public void setApartments(Apartment[] apartments) {
        this.apartments = apartments;
    }
}
