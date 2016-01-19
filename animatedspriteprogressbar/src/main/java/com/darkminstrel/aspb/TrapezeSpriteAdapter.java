package com.darkminstrel.aspb;

/**
 * Created by Dark on 19.01.2016.
 */
public final class TrapezeSpriteAdapter implements SpriteAdapter {
    @Override
    public int getSpriteIndex(float interpolatedTime, int totalSpritesCount) {
        int totalFrames = 6*totalSpritesCount;
        int frame = (int) (totalFrames*interpolatedTime);

        int ix;
        if(frame<totalSpritesCount) ix=0;
        else if(frame<2*totalSpritesCount) ix=frame-totalSpritesCount;
        else if(frame<4*totalSpritesCount) ix=totalSpritesCount-1;
        else if(frame<5*totalSpritesCount) ix=totalSpritesCount-1-(frame-4*totalSpritesCount);
        else ix=0;

        return ix;
    }
}
