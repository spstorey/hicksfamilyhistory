package spssoftware.domain;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.LinkedList;
import java.util.List;

public class Album {

    private String name;
    private DateTime sortDate;
    private String displayDate;
    private Photo coverPhoto;
    private List<Photo> photos = new LinkedList<Photo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getSortDate() {
        return sortDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDate(String date) {

        if (date.contains("-")) {
            sortDate = DateTime.parse(date, DateTimeFormat.forPattern("yyyy-MM-dd"));
        } else {
            sortDate = DateTime.parse(date, DateTimeFormat.forPattern("yyyy"));
        }

        this.displayDate = date;
    }

    public Photo getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(Photo coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

}
