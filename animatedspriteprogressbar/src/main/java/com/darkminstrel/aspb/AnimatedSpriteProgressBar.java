package com.darkminstrel.aspb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark on 15.01.2016.
 */
public class AnimatedSpriteProgressBar extends ImageView {

    public AnimatedSpriteProgressBar(Context context) {
        super(context);
        throw new IllegalArgumentException("Constructor with defined AttributeSet must be used");
    }

    public AnimatedSpriteProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedSpriteProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private int mDuration, mRotations;
    private List<Drawable> mFramesList;
    private SpriteAdapter mSpriteAdapter;
    private Animation mRotateAnimation;
    private Interpolator mInterpolator;

    private void init(Context context, AttributeSet attrs){
        if(isInEditMode()) return;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.aspb, 0, 0);
        try {
            mDuration = ta.getInteger(R.styleable.aspb_duration, 3000);

            mRotations = ta.getInteger(R.styleable.aspb_rotations, 4);

            mFramesList = new ArrayList<>();
            final int spritesArrayId = ta.getResourceId(R.styleable.aspb_sprites, 0);
            if (spritesArrayId != 0) {
                TypedArray sprites = context.getResources().obtainTypedArray(spritesArrayId);
                int spritesLen = sprites.length();
                for (int i = 0; i < spritesLen; i++) mFramesList.add(getResources().getDrawable(sprites.getResourceId(i, 0)));
                sprites.recycle();
            }
            if(mFramesList.isEmpty()) throw new IllegalArgumentException("No sprites provided");

            int adapter = ta.getInt(R.styleable.aspb_adapter, 0);
            if(adapter == 0) mSpriteAdapter = new TrapezeSpriteAdapter();
            else if(adapter == 1) mSpriteAdapter = new TriangleSpriteAdapter();
            else if(adapter == 2) mSpriteAdapter = new SquareSpriteAdapter();
            else throw new IllegalArgumentException("Unknown sprite adapter");

            int interpolator = ta.getInt(R.styleable.aspb_interpolator, 0);
            if(interpolator == 0) mInterpolator = new AccelerateDecelerateInterpolator();
            else if(interpolator == 1) mInterpolator = new LinearInterpolator();
            else throw new IllegalArgumentException("Unknown interpolator");
        } finally {
            ta.recycle();
        }

        final AnimationDrawable mAnimationDrawable = new AnimationDrawable();
        for(Drawable d : mFramesList) mAnimationDrawable.addFrame(d, 0);
        mRotateAnimation = new RotateAnimation(0, 360*mRotations, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f){
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                int ix = mSpriteAdapter.getSpriteIndex(interpolatedTime, mFramesList.size());
                mAnimationDrawable.selectDrawable(ix);
                super.applyTransformation(interpolatedTime, t);
            }
        };
        mRotateAnimation.setDuration(mDuration);
        mRotateAnimation.setRepeatCount(Animation.INFINITE);
        mRotateAnimation.setInterpolator(mInterpolator);
        setImageDrawable(mAnimationDrawable);
    }

    @Override
    public void setVisibility(int v) {
        if (getVisibility() != v) {
            super.setVisibility(v);
            if (v == GONE || v == INVISIBLE) {
                stopAnimation();
            } else {
                startAnimation();
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAnimation();
        super.onDetachedFromWindow();
    }

    void startAnimation() {
        if (getVisibility() != VISIBLE) return;
        startAnimation(mRotateAnimation);
        postInvalidate();
    }

    void stopAnimation() {
        clearAnimation();
        postInvalidate();
    }
}
