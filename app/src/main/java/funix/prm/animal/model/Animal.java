package funix.prm.animal.model;

import android.graphics.Bitmap;

public class Animal {
    private final Bitmap photo;
    private final Bitmap photoBg;
    private final String path;
    private final String name;
    private final String content;
    private boolean isFav;

    public void setFav(boolean fav) { isFav = fav; }
    public boolean isFav() { return isFav; }

    public Animal(String path, String name, Bitmap photo, Bitmap photoBg, String content) {
        this.path = path;
        this.name = name;
        this.photo = photo;
        this.photoBg = photoBg;
        this.content = content;
        this.isFav = isFav;
    }

    public String getPath() {
        return path;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public Bitmap getPhotoBg() {
        return photoBg;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
