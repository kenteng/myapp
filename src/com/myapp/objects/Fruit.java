package com.myapp.objects;

/**
 * Created by megatron on 15/2/19.
 */
public class Fruit {
    String name;
    int imageId;
    public Fruit(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public String getName(){return name;}
    public int getImageId(){return imageId;}
}
