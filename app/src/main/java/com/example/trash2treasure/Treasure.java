package com.example.trash2treasure;

public class Treasure {
    private String treasure;
    private int num;

    public Treasure (){
        this.treasure = "";
        this.num = 0;
    }

    public Treasure (String treasure,int num){
        this.num = num;
        this.treasure = treasure;
    }

    public String getTreasure() {
        return treasure;
    }

    public void setTreasure(String treasure) {
        this.treasure = treasure;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
