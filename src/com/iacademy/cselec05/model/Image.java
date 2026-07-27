package com.iacademy.cselec05.model;

import java.util.ArrayList;

public class Image {
    private String artName;
    private int numLikes;
    private User user;

    // The game plan here is we are going to store the likers (the users) into a list
    // With a datatype of type of integer
    // If user id matches with a number an integer within the array list --> ignore
    private ArrayList<Integer> listOfLikers;


    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
