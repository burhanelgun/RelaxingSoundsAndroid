package com.be.b.challange.challange.models;

public class LibraryItemModel {
    String title;//id
    String imageURL;

    public LibraryItemModel(String imageURL,String title){
        this.imageURL=imageURL;
        if(title.equals(new String("Kuş Sesleri"))){
            this.title = new String("Bird Sounds");
        }
        else if(title.equals(new String("Piyano Sesleri"))){
            this.title = new String("Piano Sounds");
        }
        else if(title.equals(new String("Doğa Sesleri"))){
            this.title = new String("Nature Sounds");
        }
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setTitle(String title) {
        if(title.equals(new String("Kuş Sesleri"))){
            this.title = new String("Bird Sounds");
        }
        else if(title.equals(new String("Piyano Sesleri"))){
            this.title = new String("Piano Sounds");
        }
        else if(title.equals(new String("Doğa Sesleri"))){
            this.title = new String("Nature Sounds");
        }

    }

    public String getTitle() {
        return title;
    }
}
