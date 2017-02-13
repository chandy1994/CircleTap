package com.chandy.circletap.circletap.MVP.Presenter;

import android.os.Handler;
import android.widget.TextView;

import com.chandy.circletap.circletap.MVP.Model.IRecord;
import com.chandy.circletap.circletap.MVP.Model.Record;
import com.chandy.circletap.circletap.MVP.View.IMenuView;

/**
 * Created by Chandy on 2017/2/13.
 */

public class MenuPresenter {
    IMenuView iMenuView;
    IRecord iRecord;
    boolean enable=true;

    public MenuPresenter(IMenuView iMenuView){
        this.iMenuView=iMenuView;
        iRecord=new Record(0,0);

    }

    public boolean isSoundEnable() {
        return enable;
    }

    public void setSoundEnable(boolean enable) {
        this.enable = enable;
        if(this.enable==true){
            iMenuView.playMusic();
        }else {
            iMenuView.pauseMusic();
        }
    }

    public void startGame(){
        iMenuView.startGameActivity();
    }

    public void readRecord(){
        iMenuView.readRecord(iRecord.ReadLast(iMenuView.getContext()),iRecord.ReadBest(iMenuView.getContext()));
    }

    public void startAnimation(){
        iMenuView.startAnimation();
    }

    public void reStartAnimation(){
        iMenuView.reStartAnimation();
    }

    public void startMusic(){
        iMenuView.playMusic();
    }

    public void pauseMusic(){
        iMenuView.pauseMusic();
    }

    public void releaseMusic(){
        iMenuView.releasePlayer();
    }


}
