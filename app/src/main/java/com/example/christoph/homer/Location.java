package com.example.christoph.homer;

/**
 * Created by Christoph on 03.10.2015.
 */
public class Location {

  private   String address;
    private  String city;
    private  String state;
    private  String country;
    private  String postalCode;
    private  String knownName;

    public Location(){

    }

    public Location(String add, String cit, String stat, String count, String post, String kn){
        address=add;
        city=cit;
        state=stat;
        country=count;
        postalCode=post;
        knownName=kn;
    }

    public String getAddress(){
        return address;
    }

    public String getCity(){
        return  city;
    }
    public String getState(){
        return state;
    }
    public String getCountry(){
        return country;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public String getKnownName(){
        return knownName;
    }

    public String serialise(){
        String str;
        str=address+";"+city+";"+state+";"+country+";"+postalCode+";"+knownName;
        return str;
    }

    public void desirialise(String str){
        String[] parts = str.split("\\;");
        if(parts.length>=6) {
            address = parts[0];
            city = parts[1];
            state = parts[2];
            country = parts[3];
            postalCode = parts[4];
            knownName = parts[5];
        }
    }
}
