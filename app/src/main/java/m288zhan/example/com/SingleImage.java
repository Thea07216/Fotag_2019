package m288zhan.example.com;


import android.graphics.Bitmap;

public class SingleImage{
    int id;
    int rating = 0;
    Bitmap bitmap;
    MModel model;

    public SingleImage(int id, int rating, Bitmap bitmap,MModel model){
        this.id = id;
        this.rating = rating;
        this.model = model;
        this.bitmap = bitmap;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}