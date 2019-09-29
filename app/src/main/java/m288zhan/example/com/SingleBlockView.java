package m288zhan.example.com;


import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;



public class SingleBlockView {
    public View view;
    public RatingBar five_star_rating;
    public SingleImage singleimage;
    public MModel model;

    public SingleBlockView(View view, final SingleImage singleimage, MModel model) {
        this.model = model;
        this.singleimage = singleimage;
        this.view = view;
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(view.getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.setContentView(R.layout.enlarge);
                ImageView imageView = dialog.findViewById(R.id.enlarge_view);
                imageView.setImageBitmap(singleimage.bitmap);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.show();
            }
        });
    }


    public void update () {
        this.five_star_rating = this.view.findViewById(R.id.five_star_rating);
        this.five_star_rating.setRating(this.singleimage.rating);
        this.five_star_rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int star_rating = (int) five_star_rating.getRating();
                singleimage.setRating((int) star_rating);
                int cur_id = singleimage.id;
                model.Images.get(cur_id - 1).setRating((int) star_rating);
                model.callObservers();
            }
        });
        ImageView imageView = this.view.findViewById(R.id.each_image);
        imageView.setImageBitmap(this.singleimage.bitmap);
    }
}