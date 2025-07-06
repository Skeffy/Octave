package io.github.skeffy.octave.model;

public class Music {

    private String type;
    private String artist;
    private String link;
    private String art;

    public Music(String type, String artist, String link, String art) {
        this.type = type;
        this.artist = artist;
        this.link = link;
        this.art = art;
    }

    public Music() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }
}
