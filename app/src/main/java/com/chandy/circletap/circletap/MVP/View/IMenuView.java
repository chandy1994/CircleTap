package com.chandy.circletap.circletap.MVP.View;

import android.content.Context;

/**
 * Created by Chandy on 2017/2/13.
 */

public interface IMenuView {
    void startGameActivity();
    void readRecord(int last,int best);
    Context getContext();
    void startAnimation();
    void reStartAnimation();
    void playMusic();
    void pauseMusic();
    void releasePlayer();
}
