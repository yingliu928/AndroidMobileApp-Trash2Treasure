package com.example.trash2treasure;

public class User {
    private String username;
    private Trash trash;
    private Treasure treasure;
    private double lat;
    private double lng;
    public User(){

    }
    public User(String name){
        this.username=name;
        this.trash = new Trash();
        this.treasure = new Treasure();

    }
    public User(String username,Trash trash,Treasure treasure,double lat,double lng){
        this.username = username;
        this.trash = trash;
        this.treasure = treasure;
        this.lat= lat;
        this.lng = lng;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Trash getTrash() {
        return trash;
    }

    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }
}
