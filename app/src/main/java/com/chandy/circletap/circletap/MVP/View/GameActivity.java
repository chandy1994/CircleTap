package com.chandy.circletap.circletap.MVP.View;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.chandy.circletap.circletap.MVP.Presenter.GamePresenter;
import com.chandy.circletap.circletap.R;
import com.chandy.circletap.circletap.Widget.Circle;

public class GameActivity extends Activity implements IGameView{
    Circle circle;
    public GamePresenter gamePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_game);


        circle=(Circle)findViewById(R.id.circle);

        gamePresenter=new GamePresenter(this);
        gamePresenter.setHandler();
        gamePresenter.play();

    }

    @Override
    public void startGame() {
        circle.playBounce();
    }

    @Override
    public void setHandler(Handler handler) {
        circle.setHandler(handler);
    }

    @Override
    public void onFinish() {
        GameActivity.this.finish();
        overridePendingTransition(0,R.anim.alpha_out);
    }

    @Override
    public Context getContext() {
        return GameActivity.this;
    }
}
