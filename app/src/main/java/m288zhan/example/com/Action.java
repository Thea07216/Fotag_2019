package m288zhan.example.com;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RatingBar;

import java.util.Observable;
import java.util.Observer;

public class Action implements Observer {
    public MModel model;
    View view;
    public Boolean flag;
    public RatingBar five_star_rating;

    @Override
    public void update(Observable observable, Object o) {

    }

    public Action(Context a, MModel m) {
        this.model = m;
        flag = Boolean.FALSE;
        this.view = ((Activity) a).findViewById(R.id.title);
        five_star_rating = (RatingBar) ((Activity) a).findViewById(R.id.cur_rating);
        five_star_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int newRatings = (int) five_star_rating.getRating();
                model.updateCurFilter(newRatings);enlarge_view
    }
}