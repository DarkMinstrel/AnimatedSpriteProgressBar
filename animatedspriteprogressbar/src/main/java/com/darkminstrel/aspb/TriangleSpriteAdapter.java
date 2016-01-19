package com.darkminstrel.aspb;

/**
 * Created by Dark on 19.01.2016.
 */
public class TriangleSpriteAdapter implements SpriteAdapter {
    @Override
    public int getSpriteIndex(float interpolatedTime, int totalSpritesCount) {
        int totalFrames = totalSpritesCount*2-2;
        int frame = (int) (totalFrames*interpolatedTime);

        int ix;
        if(frame<totalFrames/2) ix=frame;
        else ix=totalFrames-frame;

        return ix;
    }
}
