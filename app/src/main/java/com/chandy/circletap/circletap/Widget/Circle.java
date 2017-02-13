package com.chandy.circletap.circletap.Widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.chandy.circletap.circletap.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Chandy on 2017/2/9.
 */

public class Circle extends FrameLayout {

    public static int FINISHED=1;

    Context mcontext;
    Arc arc1,arc2,arc3,arc4,arc5,arc6;
    Ball ball;
    ArrayList<Arc> arcs;
    Arc bottomArc;
    Handler handler;
    MediaPlayer mediaPlayer;

    int screenWidth=getResources().getDisplayMetrics().widthPixels;
    int screenHeight=getResources().getDisplayMetrics().heightPixels;
    int left=getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
    int top=getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);



    public Circle(Context context) {
        this(context,null);
    }

    public Circle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mcontext=context;
        initView();

    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void clockWise(){
        PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("rotation",arc4.getDegree(),arc4.getDegree()+60);


        for(Arc a:arcs){
            ObjectAnimator.ofPropertyValuesHolder(a,p1).setDuration(150).start();
            a.setBottom(false);
            a.setDegree(a.getDegree()+60==360?0:a.getDegree()+60);
            if(a.getDegree()==60){
                a.setBottom(true);
                bottomArc=a;
            }
        }

    }

    public void antiClockWise(){
        PropertyValuesHolder p1=PropertyValuesHolder.ofFloat("rotation",arc4.getDegree(),arc4.getDegree()-60);

        for(Arc a:arcs){
            ObjectAnimator.ofPropertyValuesHolder(a,p1).setDuration(150).start();
            a.setBottom(false);
            a.setDegree(a.getDegree()-60<0?300:a.getDegree()-60);
            if(a.getDegree()==60){
                a.setBottom(true);
                bottomArc=a;
            }
        }

    }

    public void playWave(){
        for(Arc a:arcs){
            if(a.isBottom()){
                a.wave();
            }
        }
    }

    public void playBounce(){
        ball.success();
    }

    public void initView(){
        arcs=new ArrayList<>();
        LayoutParams layoutParams = new LayoutParams(screenWidth, screenHeight);


        ball=new Ball(mcontext);

        arc1=new Arc(mcontext);
        arc2=new Arc(mcontext);
        arc3=new Arc(mcontext);
        arc4=new Arc(mcontext);
        arc5=new Arc(mcontext);
        arc6=new Arc(mcontext);
        arcs.add(arc1);
        arcs.add(arc2);
        arcs.add(arc3);
        arcs.add(arc4);
        arcs.add(arc5);
        arcs.add(arc6);



        arc1.setColor(getResources().getColor(android.R.color.black));
        arc1.setmColor(MyColor.BLACK);
        arc1.setStartDegree(180);

        arc2.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        arc2.setmColor(MyColor.BLUE);
        arc2.setStartDegree(240);

        arc3.setColor(getResources().getColor(android.R.color.darker_gray));
        arc3.setmColor(MyColor.GRAY);
        arc3.setStartDegree(300);

        arc4.setColor(getResources().getColor(android.R.color.holo_purple));
        arc4.setmColor(MyColor.PURPLE);
        arc4.setStartDegree(0);

        arc5.setColor(getResources().getColor(android.R.color.holo_orange_light));
        arc5.setmColor(MyColor.ORANGE);
        arc5.setStartDegree(60);
        bottomArc=arc5;
        arc5.setBottom(true);

        arc6.setColor(getResources().getColor(android.R.color.holo_green_light));
        arc6.setmColor(MyColor.GREEN);
        arc6.setStartDegree(120);

        for(Arc a:arcs){
            addView(a,layoutParams);
        }

        addView(ball,layoutParams);

        mediaPlayer=MediaPlayer.create(mcontext,R.raw.bounce);




    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for(Arc a:arcs){
            a.setPivotX(screenWidth/2);
            a.setPivotY(screenWidth/2);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(ev.getRawX()>screenWidth/2){
                    antiClockWise();
                }else {
                    clockWise();
                }
                break;
        }
        return true;
    }





    public class Arc extends View {
        Paint paint;
        int degree;
        boolean isBottom=false;
        MyColor mColor;

        public Arc(Context context) {
            this(context,null);
        }

        public Arc(Context context, AttributeSet attrs) {
            this(context, attrs,0);
        }

        public Arc(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        public void setStartDegree(int degree){
            this.degree=degree;
        }

        public void setColor(int color){
            paint.setColor(color);
//            switch (color){
//                case android.R.color.black:
//                    mColor=MyColor.BLACK;
//                    break;
//                case android.R.color.darker_gray:
//                    mColor=MyColor.GRAY;
//                    break;
//                case android.R.color.holo_blue_bright:
//                    mColor=MyColor.BLUE;
//                    break;
//                case android.R.color.holo_purple:
//                    mColor=MyColor.PURPLE;
//                    break;
//                case android.R.color.holo_orange_light:
//                    mColor=MyColor.ORANGE;
//                    break;
//                case android.R.color.holo_green_light:
//                    mColor=MyColor.GREEN;
//                    break;
//            }
        }

        public void setmColor(MyColor mColor) {
            this.mColor = mColor;
        }

        public void initView(){
            paint=new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(102);
            setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }

        public boolean isBottom() {
            return isBottom;
        }

        public void setBottom(boolean bottom) {
            isBottom = bottom;
        }

        public MyColor getmColor() {
            return mColor;
        }

        public void wave(){

            ObjectAnimator down=ObjectAnimator.ofFloat(this,"TranslationY",0,30);
            ObjectAnimator up=ObjectAnimator.ofFloat(this,"TranslationY",30,0);
            AnimatorSet animatorSet=new AnimatorSet();
            animatorSet.setDuration(100);
            animatorSet.play(up).after(down);
            animatorSet.start();

        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            setMeasuredDimension(screenWidth,screenHeight);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawArc(new RectF(left*2,top*2,screenWidth-2*left,screenWidth-2*top),this.degree,60,false,paint);
            super.onDraw(canvas);

        }
    }


    public class Ball extends View{
        int count=0;
        Paint paint;
        Paint drawText;

        ObjectAnimator fall;
        ObjectAnimator rise;

        MyColor myColor=MyColor.BLACK;


        public Ball(Context context) {
            this(context,null);
        }

        public Ball(Context context, AttributeSet attrs) {
            this(context, attrs,0);
        }

        public Ball(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        public void initView(){
            drawText=new Paint();
            paint=new Paint();
            paint.setColor(Color.BLACK);
            drawText.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            drawText.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            drawText.setStyle(Paint.Style.FILL);
            drawText.setTextSize(64);
            drawText.setTextAlign(Paint.Align.CENTER);
            drawText.setStrokeWidth(10);

        }

        public MyColor randomColor(){
            Random random=new Random();
            random.setSeed(System.currentTimeMillis());
            int number=random.nextInt(6);
            MyColor color=MyColor.BLACK;
            switch (number){
                case 0:color=MyColor.BLACK;
                    break;
                case 1:color=MyColor.BLUE;
                    break;
                case 2:color=MyColor.GRAY;
                    break;
                case 3:color=MyColor.GREEN;
                    break;
                case 4:color=MyColor.ORANGE;
                    break;
                case 5:color=MyColor.PURPLE;
                    break;
            }
            return color;
        }

        public void success(){
            fall=ObjectAnimator.ofFloat(this,"TranslationY",0,screenWidth/2-left*2-100);
            rise=ObjectAnimator.ofFloat(this,"TranslationY",screenWidth/2-left*2-100,0);

            fall.setInterpolator(new AccelerateInterpolator());
            rise.setInterpolator(new DecelerateInterpolator());

            fall.setDuration(600);
            rise.setDuration(600);

            fall.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    //颜色正确
                    if(correct(bottomArc)){
                        playWave();
                        rise.start();
                        count++;
                        myColor=randomColor();
                        setDrawColor(myColor);
                        mediaPlayer.start();
                    }else {
                        fail();
                    }

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            rise.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    fall.start();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            fall.start();
        }

        public void fail(){
            //alpha 1 --- 0 !
            ObjectAnimator alpha=ObjectAnimator.ofFloat(this,"Alpha",1,0);
            ObjectAnimator down=ObjectAnimator.ofFloat(this,"TranslationY",screenWidth/2-left*2-100,screenWidth/2-left*2+260);
            PropertyValuesHolder propertyValuesHolder=PropertyValuesHolder.ofFloat("Alpha",1,0);
            ObjectAnimator set;

            down.setDuration(300);
            down.setInterpolator(new LinearInterpolator());
            down.start();

            alpha.setDuration(300);
            alpha.setInterpolator(new LinearInterpolator());
            alpha.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    Message message=Message.obtain();
                    message.what=FINISHED;
                    message.arg1=getCount();
                    handler.sendMessage(message);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            alpha.start();
            for(Arc a:arcs){
                set=ObjectAnimator.ofPropertyValuesHolder(a,propertyValuesHolder);
                set.setDuration(300);
                set.setInterpolator(new LinearInterpolator());
                set.start();
            }
        }

        public int getCount() {
            return count;
        }

        public boolean correct(Arc arc){
            if(arc.getmColor()==myColor){
                return true;
            }else {
                return false;
            }
        }

        public void setDrawColor(MyColor color){
            int penColor=getResources().getColor(android.R.color.black);
            if(color==MyColor.BLACK){
                penColor=getResources().getColor(android.R.color.black);
            }
            if(color==MyColor.BLUE){
                penColor=getResources().getColor(android.R.color.holo_blue_bright);
            }
            if(color==MyColor.GRAY){
                penColor=getResources().getColor(android.R.color.darker_gray);
            }
            if(color==MyColor.ORANGE){
                penColor=getResources().getColor(android.R.color.holo_orange_light);
            }
            if(color==MyColor.GREEN){
                penColor=getResources().getColor(android.R.color.holo_green_light);
            }
            if(color==MyColor.PURPLE){
                penColor=getResources().getColor(android.R.color.holo_purple);
            }
            paint.setColor(penColor);
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint.FontMetricsInt fontMetrics = drawText.getFontMetricsInt();
            int baseline = (screenWidth - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawCircle(screenWidth/2,screenWidth/2,100,paint);
            canvas.drawText(count+"",screenWidth/2,baseline,drawText);
            super.onDraw(canvas);
        }
    }

    public enum MyColor
    {
        BLACK,
        GRAY,
        BLUE,
        ORANGE,
        GREEN,
        PURPLE;
    }

}
