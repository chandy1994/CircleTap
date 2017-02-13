package com.chandy.circletap.circletap.MVP.View;

import android.content.Context;
import android.os.Handler;

/**
 * Created by Chandy on 2017/2/13.
 */

public interface IGameView {
    void onFinish();
    void setHandler(Handler handler);
    void startGame();
    Context getContext();
}
