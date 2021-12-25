package com.mid.task;

public class Course {
    String name ,image ,about,link;

    public  Course(String name, String image,String about,String link) {
        this.name = name;
        this.image = image;
        this.about=about;
        this.link=link;
    }

    public  Course() {
    }
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link =link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
