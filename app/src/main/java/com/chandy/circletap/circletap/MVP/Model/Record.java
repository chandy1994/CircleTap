package com.chandy.circletap.circletap.MVP.Model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Chandy on 2017/2/12.
 */

public class Record implements IRecord{
    int last=0;
    int best=0;
    public Record(int last,int best){
        this.last=last;
        this.best=best;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getBest() {
        return best;
    }

    public void setBest(int best) {
        this.best = best;
    }

    @Override
    public int ReadLast(Context context) {
        SharedPreferences sharedPreferences=context.getSharedPreferences("record",Context.MODE_PRIVATE);
        last=sharedPreferences.getInt("last",0);
        return last;
    }

    @Override
    public int ReadBest(Context context) {
        SharedPreferences sharedPreferences=context.getSharedPreferences("record",Context.MODE_PRIVATE);
        best=sharedPreferences.getInt("best",0);
        return best;
    }

    @Override
    public void WriteLast(Context context,int last) {
        this.last=last;
        SharedPreferences sharedPreferences=context.getSharedPreferences("record",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("last",last);
        editor.apply();
    }

    @Override
    public void WriteBest(Context context,int best) {
        this.best=best;
        SharedPreferences sharedPreferences=context.getSharedPreferences("record",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("best",best);
        editor.apply();
    }
}
