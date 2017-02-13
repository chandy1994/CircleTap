package com.chandy.circletap.circletap.MVP.Presenter;

import android.os.Handler;
import android.os.Message;

import com.chandy.circletap.circletap.MVP.Model.IRecord;
import com.chandy.circletap.circletap.MVP.Model.Record;
import com.chandy.circletap.circletap.MVP.View.GameActivity;
import com.chandy.circletap.circletap.MVP.View.IGameView;
import com.chandy.circletap.circletap.Widget.Circle;

/**
 * Created by Chandy on 2017/2/13.
 */

public class GamePresenter {
    public static MyHandler handler;
    public IRecord iRecord;
    public IGameView iGameView;

    public GamePresenter(final IGameView iGameView){
        this.iGameView=iGameView;
        iRecord =new Record(0,0);
        handler=new MyHandler();

    }

    public void setHandler(){
        iGameView.setHandler(handler);
    }

    public void play(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iGameView.startGame();
            }
        },1000);
    }

    






    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if(msg.what== Circle.FINISHED){
                iGameView.onFinish();
                int last=msg.arg1;
                if(last>iRecord.ReadBest(iGameView.getContext())){
                    iRecord.WriteBest(iGameView.getContext(),last);
                }
                iRecord.WriteLast(iGameView.getContext(),last);

            }
            super.handleMessage(msg);

        }
    }

}
