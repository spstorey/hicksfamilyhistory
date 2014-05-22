package spssoftware.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Album {

    private String name;
    private LocalDate sortDate;
    private String displayDate;
    private Photo coverPhoto;
    private List<Photo> photos = new LinkedList<Photo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getSortDate() {
        return sortDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDate(String date) {

        if (date.contains("-")) {
            sortDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else {
            sortDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy"));
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
