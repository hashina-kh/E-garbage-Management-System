package com.example.trashtracker;


public class Contributedatamodel {

    String id,wastetype,quantity,mobilenumber,latitude,longitude,description;


    public Contributedatamodel(String id ,String wastetype,String quantity,String mobilenumber,String latitude,String longitude,String description){

        this.wastetype=wastetype;
        this.quantity=quantity;
        this.mobilenumber=mobilenumber;
        this.latitude=latitude;
        this.longitude=longitude;
        this.description=description;
        this.id=id;



    }

    public String getWastetype() {
        return wastetype;
    }

    public String getid() { return id; }

    public String getQuantity() {
        return quantity;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDescription(){return description;}
}






