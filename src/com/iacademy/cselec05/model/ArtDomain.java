package com.iacademy.cselec05.model;

import java.util.ArrayList;

// Again --- class name should be pascal case.
public class ArtDomain
{
    private int artistId;
    private byte[] artPhoto;
    private String artistName;
    private String artName;
    private String convertedPicture;
    private int likeCount;

    // The game plan here is we are going to store the likers (the users) into a list
    // With a datatype of type of integer
    // If user id matches with a number an integer within the array list --> ignore
    private ArrayList<Integer> listOfLikers;

    public byte[] getArtPhoto() {
        return artPhoto;
    }

    public void setArtPhoto(byte[] artPhoto) {
        this.artPhoto = artPhoto;
    }
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }


    // THIS IS DANGEROUS -- Blob will take more memory in database
    // Will slowdown XAMPP
    public String getConvertedPicture() {
        return convertedPicture;
    }

    public void setConvertedPicture(String convertedPicture) {
        this.convertedPicture = convertedPicture;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getArtistId() {
        return artistId;
    }

}
