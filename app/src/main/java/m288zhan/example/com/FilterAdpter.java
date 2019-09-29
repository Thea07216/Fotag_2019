package m288zhan.example.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class FilterAdpter extends BaseAdapter {
    public Context c;
    public MModel model;
    public ArrayList<SingleImage> filteredimages;
    public ArrayList <SingleBlockView> singleblockviews = new ArrayList<SingleBlockView>();
    public FilterAdpter(Context c, MModel model){
        this.c = c;
        this.model = model;
        this.filteredimages = model.Images;
    }
    @Override
    public int getCount() {
        return filteredimages.size();
    }

    @Override
    public Object getItem(int pos) {
        return filteredimages.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View thirdview = LayoutInflater.from(this.c).inflate(R.layout.singleblock,viewGroup,false);
        SingleBlockView singleblockview = new SingleBlockView(thirdview,this.filteredimages.get(pos),model);
        singleblockview.update();
        this.singleblockviews.add(singleblockview);
        return singleblockview.view;
    }

    public void refresh(){
        int cur_rating = model.CurRatings;
        int model_size = model.Images.size();
        ArrayList<SingleImage> all_images = model.Images;
        filteredimages = new ArrayList<SingleImage>();
        for (int i= 0; i < model_size; i++){
            SingleImage si = all_images.get(i);
            if(si.rating >= cur_rating){
                filteredimages.add(si);
            }
        }
        this.notifyDataSetChanged();
    }
}