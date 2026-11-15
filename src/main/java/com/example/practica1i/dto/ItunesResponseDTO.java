package com.example.practica1i.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class ItunesResponseDTO implements Serializable {

    private static final long serialVersionUID = -6145369265L;

    private String wrapperType;
    private String artistName;
    private String trackName;
    private String longDescription;
    private OffsetDateTime releaseDate;

    public String getWrapperType() {
        return this.wrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public OffsetDateTime getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(OffsetDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }
}
