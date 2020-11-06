package com.example.trash2treasure;

import java.util.ArrayList;
import java.util.HashMap;

public class Trash {
    private String trash;
    private int num;

    public Trash(){
        this.trash = "";
        this.num = 0;
    }
    public Trash(String trash, int num){
        this.trash = trash;
        this.num = num;

    }

    public String getTrash() {
        return trash;
    }

    public void setTrash(String trash) {
        this.trash = trash;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
