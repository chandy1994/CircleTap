package com.chandy.circletap.circletap.MVP.View;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chandy.circletap.circletap.MVP.Presenter.MenuPresenter;
import com.chandy.circletap.circletap.R;

public class MenuActivity extends Activity implements IMenuView{
    Button play,rate;
    MenuPresenter menuPresenter;
    TextView best,last;
    TextView bestTitle,lastTitle;
    TextView title;
    int screenWidth;
    ImageButton sound;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);
        play=(Button)findViewById(R.id.play);
        rate=(Button)findViewById(R.id.rate);
        best=(TextView)findViewById(R.id.best);
        last=(TextView)findViewById(R.id.last);
        bestTitle=(TextView)findViewById(R.id.best_title);
        lastTitle=(TextView)findViewById(R.id.last_title);
        title=(TextView)findViewById(R.id.title);
        sound=(ImageButton)findViewById(R.id.sound);
        screenWidth=getResources().getDisplayMetrics().widthPixels;
        mediaPlayer=MediaPlayer.create(this,R.raw.higher);
        mediaPlayer.setLooping(true);


        menuPresenter=new MenuPresenter(this);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenter.startGame();
            }
        });
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenter.setSoundEnable(!menuPresenter.isSoundEnable());
                if(menuPresenter.isSoundEnable()){
                    sound.setImageResource(R.drawable.open);
                }else{
                    sound.setImageResource(R.drawable.close);
                }
            }
        });

        menuPresenter.startAnimation();

    }

    @Override
    public void startGameActivity() {
        Intent intent=new Intent();
        intent.setClass(MenuActivity.this,GameActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.alpha_in,0);
    }

    @Override
    protected void onResume() {
        menuPresenter.readRecord();
        menuPresenter.reStartAnimation();
        if(menuPresenter.isSoundEnable()){
            menuPresenter.startMusic();
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        menuPresenter.pauseMusic();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        menuPresenter.releaseMusic();
        super.onDestroy();
    }

    @Override
    public void readRecord(int last,int best) {
        this.last.setText(last+"");
        this.best.setText(best+"");
    }

    @Override
    public Context getContext() {
        return MenuActivity.this;
    }

    @Override
    public void startAnimation() {
        PropertyValuesHolder scaleX=PropertyValuesHolder.ofFloat("ScaleX",0.5f,1);
        PropertyValuesHolder scaleY=PropertyValuesHolder.ofFloat("ScaleY",0.5f,1);
        PropertyValuesHolder alpha=PropertyValuesHolder.ofFloat("Alpha",0,1);


        ObjectAnimator set1=ObjectAnimator.ofPropertyValuesHolder(title,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set2=ObjectAnimator.ofPropertyValuesHolder(best,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set3=ObjectAnimator.ofPropertyValuesHolder(last,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set4=ObjectAnimator.ofPropertyValuesHolder(bestTitle,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set5=ObjectAnimator.ofPropertyValuesHolder(lastTitle,scaleX,scaleY,alpha).setDuration(1000);

        PropertyValuesHolder TranslateLeft=PropertyValuesHolder.ofFloat("TranslationX",screenWidth,0);
        PropertyValuesHolder TranslateRight=PropertyValuesHolder.ofFloat("TranslationX",-screenWidth,0);
        PropertyValuesHolder TranslateBottom=PropertyValuesHolder.ofFloat("TranslationY",screenWidth,0);

        ObjectAnimator set6=ObjectAnimator.ofPropertyValuesHolder(play,TranslateLeft).setDuration(1000);
        ObjectAnimator set7=ObjectAnimator.ofPropertyValuesHolder(rate,TranslateRight).setDuration(1000);
        ObjectAnimator set8=ObjectAnimator.ofPropertyValuesHolder(sound,TranslateBottom).setDuration(1000);

        set1.start();
        set2.start();
        set3.start();
        set4.start();
        set5.start();
        set6.start();
        set7.start();
        set8.start();
    }

    @Override
    public void reStartAnimation() {
        PropertyValuesHolder scaleX=PropertyValuesHolder.ofFloat("ScaleX",0.5f,1);
        PropertyValuesHolder scaleY=PropertyValuesHolder.ofFloat("ScaleY",0.5f,1);
        PropertyValuesHolder alpha=PropertyValuesHolder.ofFloat("Alpha",0,1);


        ObjectAnimator set1=ObjectAnimator.ofPropertyValuesHolder(title,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set2=ObjectAnimator.ofPropertyValuesHolder(best,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set3=ObjectAnimator.ofPropertyValuesHolder(last,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set4=ObjectAnimator.ofPropertyValuesHolder(bestTitle,scaleX,scaleY,alpha).setDuration(1000);
        ObjectAnimator set5=ObjectAnimator.ofPropertyValuesHolder(lastTitle,scaleX,scaleY,alpha).setDuration(1000);

        set1.start();
        set2.start();
        set3.start();
        set4.start();
        set5.start();
    }

    @Override
    public void playMusic() {
        mediaPlayer.start();
    }

    @Override
    public void pauseMusic() {
        mediaPlayer.pause();
    }

    @Override
    public void releasePlayer() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
