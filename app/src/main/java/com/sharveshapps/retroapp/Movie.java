package com.sharveshapps.retroapp;

public class Movie {
    private String id;
    private PrimaryImage primaryImage; // Change ArrayList<PrimaryImage> to PrimaryImage
    private TitleText titleText; // Change ArrayList<TitleText> to TitleText

    public String getId() {
        return id;
    }

    public PrimaryImage getPrimaryImage() {
        return primaryImage;
    }

    public TitleText getTitleText() {
        return titleText;
    }
}
