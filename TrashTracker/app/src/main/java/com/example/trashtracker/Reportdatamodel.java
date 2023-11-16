package com.example.trashtracker;


public class Reportdatamodel {

   private String id,name,location,description,wid,type,mid,ward,userid,status,driverid,drivername,date,time;

    public Reportdatamodel(String id, String name, String location, String description, String wid, String type, String mid, String ward, String userid, String status, String driverid, String drivername, String date, String time) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.wid = wid;
        this.type = type;
        this.mid = mid;
        this.ward = ward;
        this.userid = userid;
        this.status = status;
        this.driverid = driverid;
        this.drivername = drivername;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getWid() {
        return wid;
    }

    public String getType() {
        return type;
    }

    public String getMid() {
        return mid;
    }

    public String getWard() {
        return ward;
    }

    public String getUserid() {
        return userid;
    }

    public String getStatus() {
        return status;
    }

    public String getDriverid() {
        return driverid;
    }

    public String getDrivername() {
        return drivername;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}






