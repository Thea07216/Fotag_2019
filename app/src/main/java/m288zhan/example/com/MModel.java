package m288zhan.example.com;

import android.content.Context;

import java.util.ArrayList;
import java.util.Observable;

public class MModel extends Observable {
    public int CurRatings;
    public Action act;
    public Context c;
    public ArrayList<SingleImage> Images = new ArrayList<SingleImage>();
    public MModel(Context c, int CurRatings){
        this.c = c;
        this.CurRatings = CurRatings;
    }
    public void updateCurFilter(int a){
        CurRatings = a;
        callObservers();
    }
    public void clearImages(){
        Images = new ArrayList<SingleImage>();
        callObservers();
    }
    public void callObservers(){
        this.setChanged();
        this.notifyObservers();
    }
}