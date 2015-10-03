package com.example.christoph.homer;

/**
 * Created by marcel on 10/4/15.
 */
public class CachedMainSettings {
    private static CachedMainSettings instance = null;

    private int savedDollarSelector;
    public int getSavedDollarSelector() {
        return savedDollarSelector;
    }

    public void setSavedDollarSelector(int savedDollarSelector) {
        this.savedDollarSelector = savedDollarSelector;
    }


    @Deprecated
    protected CachedMainSettings() {
        // Exists only to defeat instantiation.
    }
    public static CachedMainSettings getInstance() {
        if(instance == null) {
            // allowed in this particular case
            instance = new CachedMainSettings();
        }
        return instance;
    }
}
