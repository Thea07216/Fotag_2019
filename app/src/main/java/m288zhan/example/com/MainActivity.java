package m288zhan.example.com;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;


public class MainActivity extends AppCompatActivity implements Observer {

    public MModel model;
    public Action action;
    GridView img;
    FilterAdpter myadpter;
    Bitmap bitmap;
    View v;
    String URLIMAGE1 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/bunny.jpg";
    String URLIMAGE2 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/chinchilla.jpg";
    String URLIMAGE3 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/doggo.jpg";
    String URLIMAGE4 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/fox.jpg";
    String URLIMAGE5 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/hamster.jpg";
    String URLIMAGE6 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/husky.jpg";
    String URLIMAGE7 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/kitten.png";
    String URLIMAGE8 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/loris.jpg";
    String URLIMAGE9 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/puppy.jpg";
    String URLIMAGE10 = "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/sleepy.png";
    int image_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new MModel(this,0);
        model.act = action;
        model.addObserver(this);
        setContentView(R.layout.activity_main);
        v = this.findViewById(android.R.id.content);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.action = new Action(this, this.model);
        myadpter = new FilterAdpter(this,this.model);
        ImageButton loadBtn = (ImageButton) findViewById(R.id.LoadBtn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(model.Images.size() == 10 || model.Images.size() == 0) {
                    model.clearImages();
                    action.five_star_rating.setRating(0);
                    model.CurRatings = 0;
                    image_id = 0;
                    load_image();
                }
            }
        });
        ImageButton ClearBtn = (ImageButton) findViewById(R.id.ClearBtn);
        ClearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                while(true) {
                    if(model.Images.size() > 0 ) {
                        model.clearImages();
                        myadpter.refresh();
                        image_id = 0;
                        action.five_star_rating.setRating(0);
                        model.CurRatings = 0;
                        break;
                    }
                }
            }
        });
        model.callObservers();
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            img.setNumColumns(1);
        }
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            img.setNumColumns(2);
        }
    }

    public void load_image(){
        new GetImageFromURL().execute(URLIMAGE1);
        new GetImageFromURL().execute(URLIMAGE2);
        new GetImageFromURL().execute(URLIMAGE3);
        new GetImageFromURL().execute(URLIMAGE4);
        new GetImageFromURL().execute(URLIMAGE5);
        new GetImageFromURL().execute(URLIMAGE6);
        new GetImageFromURL().execute(URLIMAGE7);
        new GetImageFromURL().execute(URLIMAGE8);
        new GetImageFromURL().execute(URLIMAGE9);
        new GetImageFromURL().execute(URLIMAGE10);

    }
    public void afterfetch(){
        img = (GridView) findViewById(R.id.images);
        myadpter = new FilterAdpter(this,this.model);
        img.setAdapter(myadpter);
    }

    @Override
    public void update(Observable observable, Object o) {
        myadpter.refresh();
    }

    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap> {

        public GetImageFromURL(){

        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay = url[0];
            bitmap = null;
            try{
                InputStream srt = new java.net.URL(urldisplay).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            } catch(Exception e){
                e.printStackTrace();;
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bbitmap) {
            super.onPostExecute(bbitmap);
            image_id++;
            SingleImage unitimage = new SingleImage(image_id,0,bbitmap,model);
            model.Images.add(unitimage);
            afterfetch();
        }
    }
}
